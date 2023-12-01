package view;

import entity.Article;
import interface_adapter.GroupingViewModel;
import interface_adapter.article_retrieval.ArticleRetrievalController;
import interface_adapter.HomeState;
import interface_adapter.HomeViewModel;
import interface_adapter.article_retrieval.ArticleRetrievalPresenter;
import interface_adapter.grouping.GroupingController;
import interface_adapter.grouping.GroupingPresenter;
import interface_adapter.ranking.RankingController;
import interface_adapter.ranking.RankingPresenter;
import interface_adapter.transfer_article.TransferArticleController;
import interface_adapter.translation.TranslationController;
import use_case.ranking.RankingInteractor;
import use_case.translation.TranslationInputBoundary;

//
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
    GroupingViewModel groupingViewModel;

    private ArticleRetrievalController articleRetrievalController;
    private ArticleRetrievalPresenter articleRetrievalPresenter;
  
    private RankingController rankingController;
    private RankingPresenter rankingPresenter;
  
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    //Where the GUI is created:
    private List<String> prefCountry;
  
    private TransferArticleController transferArticleController;
  
    final private GroupingController groupingController;
  
    private JList<String> headlinesUI;


    //Where the GUI is created:
     public HomeView(ArticleRetrievalController controller,
                     HomeViewModel homeViewModel,
                     GroupingViewModel groupingViewModel,
                     GroupingController groupingController,
                     TransferArticleController transferArticleController) {
        this.articleRetrievalController = controller;
        this.groupingController = groupingController;
        this.transferArticleController = transferArticleController;
        this.homeViewModel = homeViewModel;
        this.rankingController = new RankingController(new RankingInteractor(new RankingPresenter(homeViewModel)));
        this.groupingViewModel = groupingViewModel;
        homeViewModel.addPropertyChangeListener(this);
        groupingViewModel.addPropertyChangeListener(this);


        //Page
        // Headlines
        JList<String> headlines = new JList<String>(homeViewModel.getHomeState().getHeadlinesModel()); //data has type Object[]
        headlines.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        headlines.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        headlines.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(headlines);
        listScroller.setPreferredSize(new Dimension(250, 80));
        this.add(headlines);

        JList<String> groupingHeadlines = new JList<String>(groupingViewModel.getGroupingState().getHeadlinesModel()); //data has type Object[]
        groupingHeadlines.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        groupingHeadlines.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        groupingHeadlines.setVisibleRowCount(-1);
        JScrollPane groupingListScroller = new JScrollPane(groupingHeadlines);
        groupingListScroller.setPreferredSize(new Dimension(250, 80));
        this.add(groupingHeadlines);

        headlines.addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (headlines.getSelectedIndex() == -1) {
                            System.out.println("Didn't select article.");
                        } else {
                            // Selection made.
                            System.out.println("Selected article.");


                            try {
                                Article chosenArticle = homeViewModel
                                        .getHomeState()
                                        .getArticleByHeadline(headlines.getSelectedValue());
                                transferArticleController.execute(chosenArticle);
                            } catch (Exception exception) {
                                transferArticleController.execute("Could not find article.");
                            }


                        }
                    }
                }
        );

        groupingHeadlines.addListSelectionListener(
             new ListSelectionListener() {
                 @Override
                 public void valueChanged(ListSelectionEvent e) {
                     if (groupingHeadlines.getSelectedIndex() == -1) {
                         System.out.println("Didn't select group.");
                     } else {
                         // Selection made.
                         System.out.println("Selected group.");
                         ArrayList<Article> articles = groupingViewModel.getGroupingState().getGroupings().get(groupingHeadlines.getSelectedIndex()).getArticles();
                         DefaultListModel<String> listArticles = homeViewModel.getHomeState().getHeadlinesModel();
                         listArticles.clear();
                         for (int i = 0; i < articles.size(); i++) {
                             listArticles.add(i, articles.get(i).getHeadline());
                         }
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
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.add(headlines, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        this.add(groupingHeadlines, gridBagConstraints);

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
      
        final JButton grouping = new JButton("Group");

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(refresh)) {
                    articleRetrievalController.execute(searchField.getText());
                    rankingController.execute(prefCountry, DateSearch.getText(), homeViewModel.getHomeState().getArticles());
                }
            }
        });


        final JMenuItem CountryMenu, DateMenu;
        final JCheckBox CanButton, FraButton, ChiButton;

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

        //Countries submenu
        this.prefCountry = new ArrayList<>();
        CountryMenu = new JMenu("Country");

        ButtonGroup countries = new ButtonGroup();


        CanButton = new JCheckBox("Canada");
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

        FraButton = new JCheckBox("France");
        countries.add(FraButton);
        CountryMenu.add(FraButton);
        FraButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    prefCountry.add("France");
                    System.out.println("France");
                }
                if (e.getStateChange() == ItemEvent.DESELECTED) {
                    prefCountry.remove("France");
                    System.out.println("No France");
                }
            }
        });

        ChiButton = new JCheckBox("China");
        countries.add(ChiButton);
        CountryMenu.add(ChiButton);
        ChiButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    prefCountry.add("China");
                    System.out.println("China");
                }
                if (e.getStateChange() == ItemEvent.DESELECTED) {
                    prefCountry.remove("China");
                    System.out.println("No China");
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
        DateMenu.add(DateSearch);

        PrefMenu.add(DateMenu);

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

        //Group Button
        grouping.setMnemonic(KeyEvent.VK_R);
        menuBar.add(grouping);

        return menuBar;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("articleRetrieval")) {
            HomeState state = (HomeState) evt.getNewValue();
            if (state.getArticleRetrievalError() != null) {
                JOptionPane.showMessageDialog(this, state.getArticleRetrievalError());
            }
        } else if (evt.getPropertyName().equals("transferArticleError")){
            JOptionPane.showMessageDialog(this, evt.getNewValue());
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
