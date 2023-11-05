package app;

import data_access.APIDataAccessObject;
import interface_adapter.ArticleRetrievalController;
import interface_adapter.HomeState;
import interface_adapter.HomeViewModel;
import interface_adapter.ViewManagerModel;
import use_case.ArticleRetrievalDataAccessInterface;
import view.HomeView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Application window.
        JFrame application = new JFrame("InfoLingo");

        // Menu
        final JMenuBar menuBar = getBar();
        application.setJMenuBar(menuBar);

        // menu end
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // Instantiate views.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // Keeps track of views.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        HomeViewModel homeViewModel = new HomeViewModel(new HomeState(new ArrayList<>()));
        ArticleRetrievalDataAccessInterface articleRetrievalDataAccessObject = new APIDataAccessObject();

        // Set initial view.
        HomeView homeView = ArticleRetrievalUseCaseFactory.create(viewManagerModel, homeViewModel, articleRetrievalDataAccessObject);
        views.add(homeView, homeView.viewName);

        viewManagerModel.setActiveView(homeView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }

    private static JMenuBar getBar() {
        JTextField searchField = new JTextField("Search!",20);
        searchField.setHorizontalAlignment(JTextField.CENTER);

        final JMenuBar menuBar;
        final JMenu PrefMenu;
        final JButton refresh = new JButton("Refresh/Search");
        ;
        final JMenuItem LangMenu;
        final JRadioButtonMenuItem EngButton, IceButton;
        menuBar = new JMenuBar();


        //Preferences
        PrefMenu = new JMenu("Preferences");
        PrefMenu.setMnemonic(KeyEvent.VK_P);
        menuBar.add(PrefMenu);

        //Languages submenu
        LangMenu = new JMenu("Language");
        LangMenu.setMnemonic(KeyEvent.VK_L);

        ButtonGroup languages = new ButtonGroup();
        EngButton = new JRadioButtonMenuItem("English");
        EngButton.setMnemonic(KeyEvent.VK_E);
        EngButton.setSelected(true);
        languages.add(EngButton);
        LangMenu.add(EngButton);

        IceButton = new JRadioButtonMenuItem("Icelandic");
        IceButton.setMnemonic(KeyEvent.VK_I);
        languages.add(IceButton);
        LangMenu.add(IceButton);

        PrefMenu.add(LangMenu);

        //Search Field
        menuBar.add(searchField);

        //Refresh/Search Button
        refresh.setMnemonic(KeyEvent.VK_R);
        menuBar.add(refresh);
        return menuBar;
    }
}
