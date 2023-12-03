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
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;
import java.awt.*;
import interface_adapter.ArticleState;
import interface_adapter.ArticleViewModel;
import interface_adapter.summarization.SummarizationController;

import javax.swing.*;
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
    ArticleViewModel articleViewModel;
    private TranslationController translationController;
    private SummarizationController summarizationController;
    private JTextArea summaryUI;
    private JTextArea translationUI;

    private int numWordsArticle;

    public ArticleView(TranslationController controller,
                       ArticleViewModel articleViewModel,
                       SummarizationController summarizationController) {
        this.translationController = controller;
        this.articleViewModel = articleViewModel;
        this.summarizationController = summarizationController;
        articleViewModel.addPropertyChangeListener(this);

        final JMenuBar menuBar = getBar();

        // Where the headline is displayed.
        final JLabel headline = new JLabel();
        headline.setText(articleViewModel.getArticleState().getHeadline());
        headline.setFont(headline.getFont().deriveFont(Font.BOLD, 14f));

        // Where the article is displayed.
        final JTextPane content = new JTextPane();
        content.setEditable(false);
        content.setText(articleViewModel.getArticleState().getOriginalContent());

        // Put the pane in a scroll pane.
        JScrollPane contentScrollPane = new JScrollPane(content);
        contentScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        contentScrollPane.setPreferredSize(new Dimension(250, 145));
        contentScrollPane.setMinimumSize(new Dimension(10, 10));

        // Where the summary is displayed.
        JTextArea summaryArea = new JTextArea();
        summaryArea.setEditable(false);
        summaryArea.setText("Click Summarise!");
        summaryArea.setLineWrap(true);
        summaryArea.setWrapStyleWord(true);
        summaryUI = summaryArea;

        // Put the area in a scroll pane.
        JScrollPane summaryScrollPane = new JScrollPane(summaryArea);
        summaryScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        summaryScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        summaryScrollPane.setPreferredSize(new Dimension(250, 145));
        summaryScrollPane.setMinimumSize(new Dimension(10, 10));

        // Where the summary is displayed.
        JTextArea translatedContent = new JTextArea();
        translatedContent.setEditable(false);
        translatedContent.setText("Click Translate!");
        translatedContent.setLineWrap(true);
        translatedContent.setWrapStyleWord(true);
        translationUI = translatedContent;

        // Put the area in a scroll pane.
        JScrollPane translationScrollPane = new JScrollPane(translatedContent);
        translationScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        translationScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        translationScrollPane.setPreferredSize(new Dimension(250, 145));
        translationScrollPane.setMinimumSize(new Dimension(10, 10));

        this.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        this.add(menuBar, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.add(headline, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.add(contentScrollPane, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        this.add(summaryScrollPane, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        this.add(translationScrollPane, gridBagConstraints);


    }

    private JMenuBar getBar() {
        final HashMap<String, String> languageMap = new HashMap<>();

        // ISO 639-2 Language Codes:
        languageMap.put("English", "EN");
        languageMap.put("French", "FR");
        languageMap.put("Chinese", "ZH");
        languageMap.put("Japanese", "JA");
        languageMap.put("Turkish", "TR");

        final JMenuBar translateBar = new JMenuBar();
        final JMenu LangMenu = new JMenu("Languages");
        final JButton translate = new JButton("Translate Article");

        translateBar.add(LangMenu);
        translateBar.add(translate);

        // Label for number of words
        JLabel numWordsLabel = new JLabel("Word Count: ");

        System.out.println(articleViewModel.
                getArticleState().
                getOriginalContent().
                split(" ")
                .length);

        // Number of words
        numWordsArticle = articleViewModel.
                getArticleState().
                getOriginalContent().
                split(" ")
                .length;

        numWordsArticle = Math.floorDiv(numWordsArticle, 4);
        JSlider numWords = new JSlider(Math.min(numWordsArticle, 20), numWordsArticle);
        numWords.setPaintTrack(true);

        // Making sure the spacing is valid and that maximum and minimum are feasible.
        int tickSpacing = Math.floorDiv(numWordsArticle - Math.min(numWordsArticle, 20), 10);
        numWords.setMajorTickSpacing(tickSpacing);
        numWords.setPaintLabels(true);
        numWords.setPaintTicks(true);
        numWords.setValue(Math.min(numWordsArticle, 20));

        translateBar.add(numWordsLabel);
        translateBar.add(numWords);

        final JButton summarise = getSummariseButton(translateBar);
        // Add summarisation button.
        translateBar.add(summarise);

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
                case 'F' -> // French
                        tempLanguageButton.setMnemonic(KeyEvent.VK_F);
                case 'C' -> // Chinese
                        tempLanguageButton.setMnemonic(KeyEvent.VK_C);
                case 'J' -> // Japanese
                        tempLanguageButton.setMnemonic(KeyEvent.VK_J);
                case 'T' -> // Turkish
                        tempLanguageButton.setMnemonic(KeyEvent.VK_T);
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
//                    translationController.execute(article, langSelected);
                    // Uses the overloaded method to get the article data.
                    translationController.execute(articleViewModel.getArticleState().getHeadline(), langSelected);
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

    private JButton getSummariseButton(JMenuBar menuBar) {
        final JButton summarise = new JButton("Summarise");
        summarise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(summarise)) {
                    JSlider numWords = (JSlider) menuBar.getComponent(3);
                    numWordsArticle = articleViewModel.
                            getArticleState().
                            getOriginalContent().
                            split(" ")
                            .length;

                    // In this case, the article is too short to summarise - minimum is equal to maximum.
                    if (numWords.getMinimum() == numWords.getMaximum()) {
                        JOptionPane.showMessageDialog(ArticleView.this,"This article is too short to summarise.");
                    } else {
                    int length = numWords.getValue();
//                    summarizationController.execute(articleViewModel.getArticleState().getOriginalContent(), length);
                    summarizationController.execute(articleViewModel.getArticleState().getOriginalContent(), length);}
                }
                }
        });
        return summarise;
    }

    public ArticleViewModel getArticleViewModel() {
        return articleViewModel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final HashMap<String, String> translationSuccessful = new HashMap<>();

        // ISO 639-2 Language Codes:
        translationSuccessful.put("EN", "");
        translationSuccessful.put("FR", " | Traduction réussie!");
        translationSuccessful.put("ZH", " | 翻译成功！");
        translationSuccessful.put("JA", " | 翻訳が成功しました!");
        translationSuccessful.put("TR", "Çeviri başarılı!");

        try {
            ArticleState state = (ArticleState) evt.getNewValue();

            if (evt.getPropertyName().equals("translationArticleUpdate")) {
//                JOptionPane.showMessageDialog(this,
//                        "Translation Successful!" + translationSuccessful.get(state.getTranslatedLanguage())
//                                + "\n"
//                                + state.getTranslatedHeadline()
//                                + "\n\n"
//                                + state.getTranslatedContent()
//                );

//                headlineUI.setText(state.getTranslatedHeadline());
                System.out.println(state.getTranslatedContent());
                translationUI.setText(state.getTranslatedContent());
            }
            else if (evt.getPropertyName().equals("failedTranslationArticleUpdate")) {
                JOptionPane.showMessageDialog(this, "Failed to translate article!");
            }
            else if (evt.getPropertyName().equals("summarizationUpdate")) {
                if (state.getSummarisationError() != null) {
                    JOptionPane.showMessageDialog(this, state.getSummarisationError());
                } else {
                    summaryUI.setText(state.getSummarisedContent());
                }
            }
        } catch (ClassCastException e) {

        }
    }
}