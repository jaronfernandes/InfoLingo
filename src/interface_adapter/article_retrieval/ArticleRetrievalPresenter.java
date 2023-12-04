package interface_adapter.article_retrieval;

import interface_adapter.HomeState;
import interface_adapter.HomeViewModel;
import use_case.article_retrieval.ArticleRetrievalOutputBoundary;
import use_case.article_retrieval.ArticleRetrievalOutputData;
import entity.Article;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleRetrievalPresenter implements ArticleRetrievalOutputBoundary {
    private final HomeViewModel homeViewModel;

    /**
     * This article retrieval presenter class is used to handle the output data and update the view accordingly.
     *
     * @param homeViewModel The HomeViewModel for dependency injection to update the HomeView.
     * @author Jaiz Jeeson (code)
     * @author Jaron Fernandes (javadoc)
     */
    public ArticleRetrievalPresenter(HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView(ArticleRetrievalOutputData outputData) {
        System.out.println("success");
        HomeState currentHomeState = homeViewModel.getHomeState();
        currentHomeState.setArticles(outputData.getArticles());

        // Extract headlines.
        List<String> headlines = new ArrayList<>();

        // Ensure that you MUTATE headlinesModel!
        DefaultListModel<String> headlinesModel = currentHomeState.getHeadlinesModel();
        headlinesModel.clear();
        for (Article article:
             currentHomeState.getArticles()) {
            String headline = article.getHeadline();
            headlines.add(headline);
            headlinesModel.addElement(headline);
        }

        // Set headlines.
        currentHomeState.setHeadlines(headlines);
        homeViewModel.firePropertyChanged("articleRetrieval");
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println("failed");
        HomeState currentHomeState = homeViewModel.getHomeState();
        currentHomeState.setArticleRetrievalError(error);
        homeViewModel.firePropertyChanged("articleRetrieval");
    }
}
