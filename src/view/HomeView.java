package view;

import interface_adapter.article_retrieval.ArticleRetrievalController;
import interface_adapter.HomeState;
import interface_adapter.HomeViewModel;
import interface_adapter.article_retrieval.ArticleRetrievalPresenter;
import interface_adapter.translation.TranslationController;
import use_case.translation.TranslationInputBoundary;


import javax.swing.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomeView extends JPanel implements PropertyChangeListener{
    public final String viewName = "Home";
    HomeViewModel homeViewModel;
    private ArticleRetrievalController articleRetrievalController;
    ArticleRetrievalPresenter articleRetrievalPresenter;
    private JList<String> headlinesUI;

    //Where the GUI is created:

    public HomeView(ArticleRetrievalController controller, HomeViewModel homeViewModel) {
        this.articleRetrievalController = controller;
        this.homeViewModel = homeViewModel;
        homeViewModel.addPropertyChangeListener(this);

        //Page

        // Headlines
                JList<String> headlines = new JList<String>(homeViewModel.getHomeState().getHeadlinesModel()); //data has type Object[]
                headlines.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                headlines.setLayoutOrientation(JList.HORIZONTAL_WRAP);
                headlines.setVisibleRowCount(-1);
                JScrollPane listScroller = new JScrollPane(headlines);
                listScroller.setPreferredSize(new Dimension(250, 80));
                this.add(headlines);
                headlinesUI = headlines;
                headlines.addListSelectionListener(
                        new ListSelectionListener() {
                            @Override
                            public void valueChanged(ListSelectionEvent e) {
                                if (!e.getValueIsAdjusting()) {
                                    if (headlines.getSelectedIndex() == -1) {

                                    } else {
                                        // Selection, enable the fire button.
                                    }
                                }
                            }
                        }
                );
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
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(refresh)) {
                    articleRetrievalController.execute(searchField.getText());

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
        return menuBar;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        HomeState state = (HomeState) evt.getNewValue();
    }
}