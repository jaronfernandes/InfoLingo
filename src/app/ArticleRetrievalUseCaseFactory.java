package app;

import interface_adapter.ArticleRetrievalController;
import interface_adapter.ArticleRetrievalPresenter;
import interface_adapter.HomeViewModel;
import interface_adapter.ViewManagerModel;
import use_case.ArticleRetrievalDataAccessInterface;
import use_case.ArticleRetrievalInputBoundary;
import use_case.ArticleRetrievalInteractor;
import use_case.ArticleRetrievalOutputBoundary;
import view.HomeView;

public class ArticleRetrievalUseCaseFactory {

    public static HomeView create(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel, ArticleRetrievalDataAccessInterface articleRetrievalDataAccessObject) {
        ArticleRetrievalController articleRetrievalController = createArticleRetrievalUseCase(viewManagerModel, homeViewModel, articleRetrievalDataAccessObject);
        return new HomeView(articleRetrievalController, homeViewModel);
    }

    private static ArticleRetrievalController createArticleRetrievalUseCase(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel, ArticleRetrievalDataAccessInterface articleRetrievalDataAccessObject) {
        ArticleRetrievalOutputBoundary articleRetrievalOutputBoundary = new ArticleRetrievalPresenter(homeViewModel);
        ArticleRetrievalInputBoundary articleRetrievalInputBoundary = new ArticleRetrievalInteractor(articleRetrievalOutputBoundary, articleRetrievalDataAccessObject);

        return new ArticleRetrievalController(articleRetrievalInputBoundary);
    }
}
