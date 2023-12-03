package view;

import app.ArticleRetrievalUseCaseFactory;
import data_access.APIDataAccessObject;
import interface_adapter.*;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class HomeViewArticleRetrievalTest {

    @org.junit.Test
    public void testSignupView() {

        // Copy code in main.
        APIDataAccessObject articleRetrievalDataAccessObject = new APIDataAccessObject();

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeViewModel homeViewModel = new HomeViewModel(new HomeState(new ArrayList<>()));

        GroupingViewModel groupingViewModel = new GroupingViewModel(new GroupingState(new ArrayList<>()));

        HomeView homeView = ArticleRetrievalUseCaseFactory.create(viewManagerModel, homeViewModel, articleRetrievalDataAccessObject, groupingViewModel);

        // Create UI.
        JFrame jf = new JFrame();
        jf.setContentPane(homeView);
        jf.pack();
        jf.setVisible(true);

        // Get a reference to search field and search button.
        JMenuBar menuBar = (JMenuBar) homeView.getComponent(2);
        JTextField searchField = (JTextField) menuBar.getComponent(1);
        JButton searchButton = (JButton) menuBar.getComponent(2);

        // Simulate click on search field.
        searchField.requestFocus();

        // Pause execution for 2 seconds.
        try {
            sleep(2000);
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

            // Pause execution for 2 seconds.
            try {
                sleep(2000);
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

            // Pause execution for 2 seconds.
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Check that the values are correct.
        assertEquals("roblox", searchField.getText());

        searchButton.doClick();

        // Wait for request to return.
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Check that homeViewModel contains articles found.
        assertNotEquals(homeViewModel.getHomeState().getArticles(), new ArrayList<>());
    }
}