package view;

import entity.Article;
import entity.Source;
import interface_adapter.ArticleViewModel;
import interface_adapter.article_retrieval.ArticleRetrievalController;
import interface_adapter.HomeState;
import interface_adapter.HomeViewModel;
import interface_adapter.article_retrieval.ArticleRetrievalPresenter;
import interface_adapter.translation.TranslationController;
import interface_adapter.translation.TranslationPresenter;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ArticleView extends JPanel implements PropertyChangeListener {
    public final String viewName = "Article";
    private Article article;
    ArticleViewModel articleViewModel;
    private TranslationController translationController;
    TranslationPresenter articleRetrievalPresenter;

    public ArticleView(TranslationController controller, ArticleViewModel articleViewModel) {
        // TODO: - TRANSLATION - only created the file so intellij stops complaining (for now)
        this.translationController = controller;
        this.articleViewModel = articleViewModel;
        articleViewModel.addPropertyChangeListener(this);

        // TODO: CHANGE ARTICLE TO ACTUAL ARTICLE OBJECT - FIND A WAY TO ACCESS IT!
        article = new Article(
                "TEST Article - roblox gaming 123",
                "some awesome roblox news amirite?",
                new Source("Roblox", "EN"),
                "keanu reeves",
                "https://www.youtube.com/@WarfighterXK/videos"
        );


        final JMenuBar menuBar = getBar();
        this.add(menuBar);
    }

    private JMenuBar getBar() {
        JTextField searchField = new JTextField("Search!",20);
        searchField.setHorizontalAlignment(JTextField.CENTER);

        final JMenuBar menuBar = new JMenuBar();
        final JMenu LangMenu = new JMenu("Languages");
        final JButton translate = new JButton("Translate Article");

        menuBar.add(translate);

        translate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(translate)) {
                    translationController.execute(article, "English");

                }
            }
        });
        //Preferences
        LangMenu.setMnemonic(KeyEvent.VK_L);
        menuBar.add(LangMenu);
        final JRadioButtonMenuItem EngButton, IceButton, JapButton;

        //Languages submenu
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

        JapButton = new JRadioButtonMenuItem("Japanese");
        JapButton.setMnemonic(KeyEvent.VK_J);
        languages.add(JapButton);
        LangMenu.add(JapButton);

        //Search Field
        menuBar.add(searchField);

        return menuBar;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}