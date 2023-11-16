package view;

import interface_adapter.ArticleState;
import interface_adapter.ArticleViewModel;
import interface_adapter.summarization.SummarizationController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ArticleView extends JPanel implements PropertyChangeListener {
    private SummarizationController summarizationController;
    private ArticleViewModel articleViewModel;
    public ArticleView(SummarizationController summarizationController, ArticleViewModel articleViewModel) {
        this.articleViewModel = articleViewModel;
        this.summarizationController = summarizationController;
        articleViewModel.addPropertyChangeListener(this);

        this.add(new JLabel(articleViewModel.getArticleState().getHeadline()));
        this.add(new JLabel(articleViewModel.getArticleState().getOriginalContent()));
        this.add(this.getBar());
    }

    private JMenuBar getBar() {
        final JMenuBar menuBar;
        final JMenu preferencesMenu;

        final JButton summarise = new JButton("Summarise");
        summarise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(summarise)) {
                    summarizationController.execute(articleViewModel.getArticleState().getOriginalContent(),);
                }
            }
        });

        final JMenuItem languagesDropdown;
        final JRadioButtonMenuItem english, icelandic;
        menuBar = new JMenuBar();

        //Preferences
        preferencesMenu = new JMenu("Preferences");
        preferencesMenu.setMnemonic(KeyEvent.VK_P);
        menuBar.add(preferencesMenu);

        // Number of sentences
        JTextField numSentences = new JTextField("Number of Sentences");
        preferencesMenu.add(numSentences);

        // Languages submenu
        languagesDropdown = new JMenu("Language");
        languagesDropdown.setMnemonic(KeyEvent.VK_L);

        ButtonGroup languages = new ButtonGroup();
        english = new JRadioButtonMenuItem("English");
        english.setMnemonic(KeyEvent.VK_E);
        english.setSelected(true);
        languages.add(english);
        languagesDropdown.add(english);

        icelandic = new JRadioButtonMenuItem("Icelandic");
        icelandic.setMnemonic(KeyEvent.VK_I);
        languages.add(icelandic);
        languagesDropdown.add(icelandic);

        preferencesMenu.add(languagesDropdown);

        // Add summarisation button.
        menuBar.add(summarise);

        return menuBar;
    }

    /**
     * This method gets called when a bound property is changed. Show summarisation error if applicable.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object state = evt.getNewValue();
        try {
            ArticleState articleState = (ArticleState) state;
            if (articleState.getSummarisationError() != null) {
                JOptionPane.showMessageDialog(this, articleState.getSummarisationError());
            }
        } catch (ClassCastException e) {

        }
    }
}
