package view;

import interface_adapter.ArticleState;
import interface_adapter.ArticleViewModel;
import interface_adapter.ViewManagerModel;
import use_case.transfer_article.TransferArticleOutputData;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewManager implements PropertyChangeListener {
    private final CardLayout cardLayout;
    private final JPanel views;
    private ViewManagerModel viewManagerModel;
    private HomeView homeView;


    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel, HomeView homeView) {
        this.views = views;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.homeView = homeView;
        this.homeView.addPropertyChangeListener(this);
        this.viewManagerModel.addPropertyChangeListener(this);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Some sort of event.");
        if (evt.getPropertyName().equals("view")) {

            String viewModelName = (String) evt.getNewValue();
            cardLayout.show(views, viewModelName);
        } else if (evt.getPropertyName().equals("switchArticleView")) {
            System.out.println("Recieved event to switch view.");

            TransferArticleOutputData outputData = (TransferArticleOutputData) evt.getNewValue();
            ArticleView articleView = (ArticleView) views.getComponent(1);

            ArticleViewModel articleViewModel = articleView.getArticleViewModel();
            ArticleState state = articleViewModel.getArticleState();
            state.setHeadline(outputData.getArticle().getHeadline());
            state.setOriginalContent(outputData.getArticle().getContent());

            // Retrieve components.
            JLabel headline = (JLabel) articleView.getComponent(1);
            JScrollPane contentScrollPane = (JScrollPane) articleView.getComponent(2);
            JTextPane content = (JTextPane) ((JViewport) contentScrollPane.getComponent(0)).getComponent(0);

            // Set slider size and spacing.
            JMenuBar menuBar = (JMenuBar) articleView.getComponent(0);
            JSlider numWords = (JSlider) menuBar.getComponent(3);

            int numWordsArticle = articleViewModel.
                    getArticleState().
                    getOriginalContent().
                    split(" ")
                    .length;

            numWordsArticle = Math.floorDiv(numWordsArticle, 4);

            numWords.setMinimum(Math.min(numWordsArticle, 20));
            numWords.setMaximum(numWordsArticle);
//            System.out.println(numWordsArticle);

            int tickSpacing = Math.floorDiv(numWordsArticle - Math.min(numWordsArticle, 20), 10);
//            System.out.println(tickSpacing);
            numWords.setMajorTickSpacing(tickSpacing);

            // Set headline.
            headline.setText(state.getHeadline());
            content.setText(state.getOriginalContent());

            cardLayout.show(views, "Article");
        }
    }
}
