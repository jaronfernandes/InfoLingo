package interface_adapter;

import use_case.ArticleRetrievalOutputBoundary;
import use_case.ArticleRetrievalOutputData;
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
        homeViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        HomeState currentHomeState = homeViewModel.getHomeState();
        currentHomeState.setArticleRetrievalError(error);
        homeViewModel.firePropertyChanged();
    }
}
