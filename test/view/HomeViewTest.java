package view;

import app.ArticleRetrievalUseCaseFactory;
import app.TranslationUseCaseFactory;
import data_access.APIDataAccessObject;
import data_access.SummarisationDataAccessObject;
import data_access.TranslationAPIDataAccessObject;
import interface_adapter.*;
import use_case.summarization.SummarizationDataAccessInterface;
import use_case.translation.TranslateAPIDataAccessInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class HomeViewTest {

    @org.junit.Test
    public void testSignupView() {

        // Copy code in main.
        // Application window.
        JFrame application = new JFrame("InfoLingo");

        // menu end
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // Instantiate views.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        APIDataAccessObject articleRetrievalDataAccessObject = new APIDataAccessObject(true);
        TranslateAPIDataAccessInterface translationDataAccessObject = new TranslationAPIDataAccessObject(articleRetrievalDataAccessObject);
        SummarizationDataAccessInterface summarisationDataAccessObject = new SummarisationDataAccessObject();

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeViewModel homeViewModel = new HomeViewModel(new HomeState(new ArrayList<>()));

        GroupingViewModel groupingViewModel = new GroupingViewModel(new GroupingState(new ArrayList<>()));

        HomeView homeView = ArticleRetrievalUseCaseFactory.create(viewManagerModel, homeViewModel, articleRetrievalDataAccessObject, groupingViewModel);

        views.add(homeView, homeView.viewName);

        ArticleViewModel articleViewModel = new ArticleViewModel(new ArticleState());

        ViewManager viewManager = new ViewManager(views, cardLayout, viewManagerModel, homeView);

        // Set initial view.
        ArticleView articleView = TranslationUseCaseFactory.create(
                viewManagerModel,
                articleViewModel,
                translationDataAccessObject,
                summarisationDataAccessObject
        );

        views.add(articleView, articleView.viewName);

        // Create UI.
        viewManagerModel.setActiveView(homeView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);

        // Get a reference to search field and search button.
//        Component[] components = homeView.getComponents();
        JList headlines = (JList) homeView.getComponent(0);
        JMenuBar menuBar = (JMenuBar) homeView.getComponent(2);
        JTextField searchField = (JTextField) menuBar.getComponent(1);
        JButton searchButton = (JButton) menuBar.getComponent(2);

        // Simulate click on search field.
        searchField.requestFocus();

        // Pause execution for 0.5 seconds.
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        char[] searchCharacters = {'r', 'o', 'b', 'l', 'o', 'x'};
        for (char searchCharacter: searchCharacters) {
            // Type something in.
            KeyEvent event = new KeyEvent(
                    searchField, // interacting with the searchField
                    KeyEvent.KEY_TYPED, //
                    System.currentTimeMillis(), // say the event happened right now
                    0, // no modifiers
                    KeyEvent.VK_UNDEFINED, // for KEY_TYPED, the KeyCode is undefined per documentation
                    searchCharacter); // the character that is being typed

            // Tell menu bar to type something into the search field!
            menuBar.dispatchEvent(event);

            // Pause execution.
            try {
                sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Print value in text field for debugging.
            System.out.println("Search field: " + searchField.getText());

            // Move cursor to the right so that char typed is last not first.
            KeyEvent eventRight = new KeyEvent(
                    searchField,
                    KeyEvent.KEY_PRESSED,
                    System.currentTimeMillis(),
                    0,
                    KeyEvent.VK_RIGHT,
                    KeyEvent.CHAR_UNDEFINED
            );
            searchField.dispatchEvent(eventRight);

            // Pause execution.
            try {
                sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Check that the values are correct.
        assertEquals("roblox", searchField.getText());

        searchButton.doClick();

        // Wait for request to return.
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Check that homeViewModel contains articles found.
        assertNotEquals(homeViewModel.getHomeState().getArticles(), new ArrayList<>());

        // Try redirecting to an article.
        headlines.setSelectedIndex(0);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}