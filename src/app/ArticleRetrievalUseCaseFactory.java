package app;

import interface_adapter.GroupingViewModel;
import interface_adapter.article_retrieval.ArticleRetrievalController;
import interface_adapter.article_retrieval.ArticleRetrievalPresenter;
import interface_adapter.HomeViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.ranking.RankingController;
import interface_adapter.grouping.GroupingController;
import interface_adapter.grouping.GroupingPresenter;
import interface_adapter.transfer_article.TransferArticleController;
import interface_adapter.transfer_article.TransferArticlePresenter;
import use_case.article_retrieval.ArticleRetrievalDataAccessInterface;
import use_case.article_retrieval.ArticleRetrievalInputBoundary;
import use_case.article_retrieval.ArticleRetrievalInteractor;
import use_case.article_retrieval.ArticleRetrievalOutputBoundary;
import use_case.grouping.GroupingInputBoundary;
import use_case.grouping.GroupingInteractor;
import use_case.grouping.GroupingOutputBoundary;
import use_case.transfer_article.TransferArticleInputBoundary;
import use_case.transfer_article.TransferArticleInteractor;
import use_case.transfer_article.TransferArticleOutputBoundary;
import view.ArticleView;
import view.HomeView;
import view.ViewManager;

public class ArticleRetrievalUseCaseFactory {

    public static HomeView create(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel,
                                  ArticleRetrievalDataAccessInterface articleRetrievalDataAccessObject,
                                  GroupingViewModel groupingViewModel) {
        ArticleRetrievalController articleRetrievalController = createArticleRetrievalUseCase(viewManagerModel, homeViewModel, articleRetrievalDataAccessObject);
        GroupingController groupingController = createGroupingUseCase(viewManagerModel, groupingViewModel);
        TransferArticleController transferArticleController = createTransferArticleUseCase(viewManagerModel, homeViewModel);
        return new HomeView(articleRetrievalController, homeViewModel, groupingViewModel, groupingController, transferArticleController);
    }
//
    private static ArticleRetrievalController createArticleRetrievalUseCase(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel, ArticleRetrievalDataAccessInterface articleRetrievalDataAccessObject) {
        ArticleRetrievalOutputBoundary articleRetrievalOutputBoundary = new ArticleRetrievalPresenter(homeViewModel);
        ArticleRetrievalInputBoundary articleRetrievalInputBoundary = new ArticleRetrievalInteractor(articleRetrievalOutputBoundary, articleRetrievalDataAccessObject);

        return new ArticleRetrievalController(articleRetrievalInputBoundary);
    }

    private static GroupingController createGroupingUseCase(ViewManagerModel viewManagerModel, GroupingViewModel groupingViewModel) {
        GroupingOutputBoundary groupingOutputBoundary = new GroupingPresenter(groupingViewModel);
        GroupingInputBoundary groupingInputBoundary = new GroupingInteractor(groupingOutputBoundary);

        return new GroupingController(groupingInputBoundary);
    }

    private static TransferArticleController createTransferArticleUseCase(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel) {
        TransferArticleOutputBoundary transferArticleOutputBoundary = new TransferArticlePresenter(viewManagerModel);
        TransferArticleInputBoundary transferArticleInputBoundary = new TransferArticleInteractor(transferArticleOutputBoundary);

        return new TransferArticleController(transferArticleInputBoundary);
    }
}
