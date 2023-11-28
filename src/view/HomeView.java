package view;

import interface_adapter.article_retrieval.ArticleRetrievalController;
import interface_adapter.HomeState;
import interface_adapter.HomeViewModel;
import interface_adapter.article_retrieval.ArticleRetrievalPresenter;
import interface_adapter.grouping.GroupingController;
import interface_adapter.grouping.GroupingPresenter;
import interface_adapter.ranking.RankingController;
import interface_adapter.ranking.RankingPresenter;
import interface_adapter.translation.TranslationController;
import use_case.translation.TranslationInputBoundary;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class HomeView extends JPanel implements PropertyChangeListener{
    public final String viewName = "Home";
    private HomeViewModel homeViewModel;
    private ArticleRetrievalController articleRetrievalController;
    private ArticleRetrievalPresenter articleRetrievalPresenter;
    private RankingController rankingController;
    private RankingPresenter rankingPresenter;
    private JList<String> headlinesUI;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    //Where the GUI is created:
    private List<String> prefCountry;

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

        headlines.addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (headlines.getSelectedIndex() == -1) {
                            System.out.println("Didn't select article.");
                        } else {
                            // Selection made.
                            System.out.println("Selected article.");
                            support.firePropertyChange("switchView", null, "ArticleView");
                        }
                    }
                }
        );
        headlinesUI = headlines;
        //background colour
        this.setBackground(new Color(240, 240, 240));


        // Menu
        final JMenuBar menuBar = getBar();

        // Adding stuff to UI.
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.add(headlines, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.add(menuBar, gridBagConstraints);
    }

    private JMenuBar getBar() {
        JTextField searchField = new JTextField("Search!",20);
        searchField.setHorizontalAlignment(JTextField.CENTER);

        final JMenuBar menuBar;
        final JMenu PrefMenu;
        final JButton refresh = new JButton("Refresh/Search");
        JTextField DateSearch = new JTextField("YYYY-MM-DD",20);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(refresh)) {
                    articleRetrievalController.execute(searchField.getText());
                    rankingController.execute(prefCountry, DateSearch.getText());
                }
            }
        });

        final JMenuItem CountryMenu, DateMenu;
        final JCheckBoxMenuItem CanButton, FraButton, ChiButton;
        menuBar = new JMenuBar();


        //Preferences
        PrefMenu = new JMenu("Preferences");
        PrefMenu.setMnemonic(KeyEvent.VK_P);
        menuBar.add(PrefMenu);

        //Countries submenu
        CountryMenu = new JMenu("Country");

        ButtonGroup countries = new ButtonGroup();


        CanButton = new JCheckBoxMenuItem("Canada");
        countries.add(CanButton);
        CountryMenu.add(CanButton);
        CanButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    prefCountry.add("Canada");
                    System.out.println("Canada");
                }
                if (e.getStateChange() == ItemEvent.DESELECTED) {
                    prefCountry.remove("Canada");
                    System.out.println("no Canada");
                }
            }
        });

        FraButton = new JCheckBoxMenuItem("France");
        countries.add(FraButton);
        CountryMenu.add(FraButton);
        FraButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    prefCountry.add("France");
                }
                if (e.getStateChange() == ItemEvent.DESELECTED) {
                    prefCountry.remove("France");
                }
            }
        });

        ChiButton = new JCheckBoxMenuItem("China");
        countries.add(ChiButton);
        CountryMenu.add(ChiButton);
        ChiButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    prefCountry.add("China");
                }
                if (e.getStateChange() == ItemEvent.DESELECTED) {
                    prefCountry.remove("China");
                }
            }
        });

        PrefMenu.add(CountryMenu);

        //Dates submenu
        DateMenu = new JMenu("Date");
        PrefMenu.setMnemonic(KeyEvent.VK_D);


        // Has a max amount of characters on DateSearch
        AbstractDocument dateDocument = (AbstractDocument) DateSearch.getDocument();

        dateDocument.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if ((fb.getDocument().getLength() + string.length()) <= 10) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if ((fb.getDocument().getLength() + text.length() - length) <= 10) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        PrefMenu.add(DateSearch);

        //Search Field
        menuBar.add(searchField);
        searchField.setForeground(Color.GRAY);
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search!")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search!");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });



        //Refresh/Search Button
        refresh.setMnemonic(KeyEvent.VK_R);
        menuBar.add(refresh);
        return menuBar;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("articleRetrieval")) {
            HomeState state = (HomeState) evt.getNewValue();
            if (state.getArticleRetrievalError() != null) {
                JOptionPane.showMessageDialog(this, state.getArticleRetrievalError());
            } else {
            }
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
