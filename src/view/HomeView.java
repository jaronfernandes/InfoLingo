package view;

import entity.Article;
import interface_adapter.GroupingViewModel;
import interface_adapter.article_retrieval.ArticleRetrievalController;
import interface_adapter.HomeState;
import interface_adapter.HomeViewModel;
import interface_adapter.article_retrieval.ArticleRetrievalPresenter;
import interface_adapter.grouping.GroupingController;

//
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class HomeView extends JPanel implements PropertyChangeListener{
    public final String viewName = "Home";
    HomeViewModel homeViewModel;
    GroupingViewModel groupingViewModel;
    private ArticleRetrievalController articleRetrievalController;
    final GroupingController groupingController;
    ArticleRetrievalPresenter articleRetrievalPresenter;





    //Where the GUI is created:


    public HomeView(ArticleRetrievalController controller, HomeViewModel homeViewModel,
                    GroupingViewModel groupingViewModel, GroupingController groupingController) {
        this.articleRetrievalController = controller;
        this.groupingController = groupingController;
        this.homeViewModel = homeViewModel;
        this.groupingViewModel = groupingViewModel;
        homeViewModel.addPropertyChangeListener(this);
        groupingViewModel.addPropertyChangeListener(this);


        //Page

        //Headlines
        // Menu
        final JMenuBar menuBar = getBar();
        this.add(menuBar);


        for (String headline: homeViewModel.getHomeState().getHeadlines()) {
            JLabel Headline = new JLabel(headline);
            this.add(Headline);
        }
        //this.add(news);

    }


    private JMenuBar getBar() {
        JTextField searchField = new JTextField("Search!",20);
        searchField.setHorizontalAlignment(JTextField.CENTER);

        final JMenuBar menuBar;
        final JMenu PrefMenu;
        final JButton refresh = new JButton("Refresh/Search");
        final JButton grouping = new JButton("Group");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(refresh)) {
                    articleRetrievalController.execute(searchField.getText());
                }
            }
        });

        grouping.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(grouping)) {
                    groupingController.execute((ArrayList<Article>) homeViewModel.getHomeState().getArticles());
                }
            }
        });


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
        grouping.setMnemonic(KeyEvent.VK_R);
        menuBar.add(grouping);
        return menuBar;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        HomeState state = (HomeState) evt.getNewValue();
    }
}