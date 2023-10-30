package interface_adapter;

import use_case.ArticleRetrievalOutputBoundary;
import use_case.ArticleRetrievalOutputData;

public class ArticleRetrievalPresenter implements ArticleRetrievalOutputBoundary {
    private final HomeViewModel homeViewModel;

    public ArticleRetrievalPresenter(HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView(ArticleRetrievalOutputData outputData) {
        HomeState currentHomeState = homeViewModel.getHomeState();
        currentHomeState.setArticles(outputData.getArticles());
        homeViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        HomeState currentHomeState = homeViewModel.getHomeState();
        currentHomeState.setArticleRetrievalError(error);
        homeViewModel.firePropertyChanged();
    }
}
