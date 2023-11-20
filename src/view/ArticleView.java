package view;

import entity.Article;
import entity.Source;
import interface_adapter.ArticleState;
import interface_adapter.ArticleViewModel;
import interface_adapter.article_retrieval.ArticleRetrievalController;
import interface_adapter.HomeState;
import interface_adapter.HomeViewModel;
import interface_adapter.article_retrieval.ArticleRetrievalPresenter;
import interface_adapter.translation.TranslationController;
import interface_adapter.translation.TranslationPresenter;


import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.security.Key;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

public class ArticleView extends JPanel implements PropertyChangeListener {
    public final String viewName = "Article";
    private Article article;
    ArticleViewModel articleViewModel;
    private TranslationController translationController;
    TranslationPresenter articleRetrievalPresenter;
    private JTextPane headlineUI;
    private JTextPane contentUI;

    public ArticleView(TranslationController controller, ArticleViewModel articleViewModel) {
        // TODO: - TRANSLATION - only created the file so intellij stops complaining (for now)
        this.translationController = controller;
        this.articleViewModel = articleViewModel;
        articleViewModel.addPropertyChangeListener(this);

        // TODO: CHANGE ARTICLE TO ACTUAL ORIGINAL ARTICLE OBJECT - FIND A WAY TO ACCESS IT!
        article = new Article(
                "Test Article - roblox gaming 123",
                "some awesome roblox news amirite?",
                new Source("Roblox", "EN"),
                "keanu reeves",
                "https://www.youtube.com/@WarfighterXK/videos"
        );


        final JMenuBar menuBar = getBar();

        final JTextPane headline = new JTextPane();
        headline.setEditable(false);
        headline.setText(article.getHeadline());
        headlineUI = headline;

        final JTextPane content = new JTextPane();
        content.setEditable(false);
        content.setText(article.getContent());
        contentUI = content;

        this.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        this.setLayout(new GridLayout(0, 1));

        this.add(menuBar, BorderLayout.CENTER);
        this.add(headline, BorderLayout.CENTER);
        this.add(content, BorderLayout.CENTER);
    }

    private JMenuBar getBar() {
        final HashMap<String, String> languageMap = new HashMap<>();

        // ISO 639-2 Language Codes:
        languageMap.put("English", "EN");
        languageMap.put("Icelandic", "IS");
        languageMap.put("Chinese", "ZH");
        languageMap.put("Japanese", "JA");

        final JMenuBar translateBar = new JMenuBar();
        final JMenu LangMenu = new JMenu("Languages");
        final JButton translate = new JButton("Translate Article");

        translateBar.add(LangMenu);
        translateBar.add(translate);

        //Preferences
        LangMenu.setMnemonic(KeyEvent.VK_L);

        //Languages submenu
        ButtonGroup languages = new ButtonGroup();

        for (String key : languageMap.keySet()) {
            JRadioButtonMenuItem tempLanguageButton = new JRadioButtonMenuItem(key);
            switch (key.charAt(0)) {
                case 'E' -> {
                    tempLanguageButton.setMnemonic(KeyEvent.VK_E);
                    tempLanguageButton.setSelected(true);
                }
                case 'I' -> // ICELANDIC NOT IN API
                        tempLanguageButton.setMnemonic(KeyEvent.VK_I);
                case 'J' -> // ICELANDIC NOT IN API
                        tempLanguageButton.setMnemonic(KeyEvent.VK_J);
                default -> {
                    tempLanguageButton.setMnemonic(KeyEvent.VK_0);
                }
            }

            languages.add(tempLanguageButton);
            LangMenu.add(tempLanguageButton);
        }

        translate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(translate)) {
                    String langSelected = getLanguage(languages, languageMap);
                    translationController.execute(article, langSelected);
                }
            }
        });

        return translateBar;
    }

    private String getLanguage(ButtonGroup languages, HashMap<String, String> languageMap) {
        Enumeration headerValues = languages.getElements();
        Iterator<AbstractButton> values = headerValues.asIterator();

        while (values.hasNext()) {
            AbstractButton currentButton = (JRadioButtonMenuItem) values.next();

            if (currentButton.isSelected()) {
                String language = currentButton.getText();
                return languageMap.get(language); // This is the language selected in ISO 639-2!
            }
        }

        return "EN";  // English by default.
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final HashMap<String, String> translationSuccessful = new HashMap<>();

        // ISO 639-2 Language Codes:
        translationSuccessful.put("EN", "");
        translationSuccessful.put("IS", " | Þýðing tókst!");
        translationSuccessful.put("IS", " | 翻译成功！");
        translationSuccessful.put("JA", " | 翻訳が成功しました!");

        ArticleState state = (ArticleState) evt.getNewValue();

//        System.out.println("hi property change!!");
//        System.out.println(evt.getPropertyName());

        if (evt.getPropertyName().equals("translationArticleUpdate")) {
//            JOptionPane.showMessageDialog(this, state.getUsernameError());
            JOptionPane.showMessageDialog(this,
                    "Translation Successful!" + translationSuccessful.get(state.getTranslatedLanguage())
                    + "\n"
                    + state.getTranslatedHeadline()
                    + "\n\n"
                    + state.getTranslatedContent()
            );

            headlineUI.setText(state.getTranslatedHeadline());
            contentUI.setText(state.getTranslatedContent());
        }
//        if (evt.getPropertyName().equals("clear")) {
//            System.out.println("hiii");
//            JOptionPane.showMessageDialog(this, "Cleared users:\n"+state.getUsername());
//        }
    }
}