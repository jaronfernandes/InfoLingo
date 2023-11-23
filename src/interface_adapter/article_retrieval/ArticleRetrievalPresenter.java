package interface_adapter.article_retrieval;

import interface_adapter.HomeState;
import interface_adapter.HomeViewModel;
import use_case.article_retrieval.ArticleRetrievalOutputBoundary;
import use_case.article_retrieval.ArticleRetrievalOutputData;
import entity.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRetrievalPresenter implements ArticleRetrievalOutputBoundary {
    private final HomeViewModel homeViewModel;

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
        for (Article article:
             currentHomeState.getArticles()) {
            headlines.add(article.getHeadline());
        }

        // Set headlines.
        currentHomeState.setHeadlines(headlines);
        homeViewModel.firePropertyChanged("ArticleRetrieval");
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println("failed");
        HomeState currentHomeState = homeViewModel.getHomeState();
        currentHomeState.setArticleRetrievalError(error);
        homeViewModel.firePropertyChanged("failedArticleRetrieval");
    }
}
