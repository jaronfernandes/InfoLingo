package app;

import interface_adapter.article_retrieval.ArticleRetrievalController;
import interface_adapter.article_retrieval.ArticleRetrievalPresenter;
import interface_adapter.HomeViewModel;
import interface_adapter.ViewManagerModel;
import use_case.article_retrieval.ArticleRetrievalDataAccessInterface;
import use_case.article_retrieval.ArticleRetrievalInputBoundary;
import use_case.article_retrieval.ArticleRetrievalInteractor;
import use_case.article_retrieval.ArticleRetrievalOutputBoundary;
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
