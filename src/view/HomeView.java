package view;

import entity.Article;
import interface_adapter.GroupingViewModel;
import interface_adapter.article_retrieval.ArticleRetrievalController;
import interface_adapter.HomeState;
import interface_adapter.HomeViewModel;
import interface_adapter.article_retrieval.ArticleRetrievalPresenter;
import interface_adapter.grouping.GroupingController;
import interface_adapter.translation.TranslationController;
import use_case.translation.TranslationInputBoundary;

//
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
    final GroupingController groupingController;
    private ArticleRetrievalPresenter articleRetrievalPresenter;
    private JList<String> headlinesUI;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


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
                            support.firePropertyChange("switchView", null, "ArticleView");
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
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}