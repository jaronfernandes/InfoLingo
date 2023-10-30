package view;

import interface_adapter.ArticleRetrievalController;
import interface_adapter.HomeState;
import interface_adapter.HomeViewModel;
import interface_adapter.ArticleRetrievalPresenter;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class HomeView extends JPanel implements PropertyChangeListener{
    public final String viewName = "Home";
    private final JTextField searchField = new JTextField(20);
    HomeViewModel homeViewModel;
    ArticleRetrievalController articleRetrievalController;
    ArticleRetrievalPresenter articleRetrievalPresenter;




    //Where the GUI is created:
    JMenuBar menuBar;
    JMenu PrefMenu, Refresh, Search;
    JMenuItem LangMenu;
    JRadioButtonMenuItem EngButton, IceButton;
    JFrame frame;
    JPanel news;

    public HomeView(ArticleRetrievalController controller, HomeViewModel homeViewModel) {
        this.articleRetrievalController = controller;
        this.homeViewModel = homeViewModel;
        homeViewModel.addPropertyChangeListener(this);



        // Menu section
            //Create the menu bar.
        menuBar = new JMenuBar();
        frame = new JFrame();


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
        PrefMenu.add(IceButton);


            //Refresh/Search Button
        Refresh = new JMenu("Refresh/Search");
        Refresh.setMnemonic(KeyEvent.VK_R);
        menuBar.add(Refresh);

        //Page

        //Headlines

        JTextField searchField = new JTextField("Search!");
        frame.add(searchField);

        for (String headline: homeViewModel.getHomeState().getHeadlines()) {
            JLabel Headline = new JLabel(headline);
            frame.add(Headline);
        }

        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.pack();
        frame.setVisible(true);
        Refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                articleRetrievalController.execute(searchText);
            }
        });


    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        HomeState state = (HomeState) evt.getNewValue();
    }
}