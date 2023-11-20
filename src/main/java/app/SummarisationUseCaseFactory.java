package app;

import interface_adapter.ArticleState;
import interface_adapter.ArticleViewModel;
import interface_adapter.summarization.SummarizationController;
import interface_adapter.ViewManagerModel;
import interface_adapter.summarization.SummarizationController;
import interface_adapter.summarization.SummarizationPresenter;
import use_case.article_retrieval.ArticleRetrievalDataAccessInterface;
import use_case.summarization.SummarizationDataAccessInterface;
import use_case.summarization.SummarizationInputBoundary;
import use_case.summarization.SummarizationInteractor;
import use_case.summarization.SummarizationOutputBoundary;
import view.ArticleView;

public class SummarisationUseCaseFactory {
    public static ArticleView create(ViewManagerModel viewManagerModel, ArticleViewModel articleViewModel, SummarizationDataAccessInterface summarizationDataAccessObject) {
        SummarizationController summarizationController = createSummarisationUseCase(viewManagerModel, articleViewModel, summarizationDataAccessObject);
        return new ArticleView(summarizationController, articleViewModel);
    }

    private static SummarizationController createSummarisationUseCase(ViewManagerModel viewManagerModel, ArticleViewModel articleViewModel, SummarizationDataAccessInterface summarizationDataAccessObject) {
        SummarizationOutputBoundary summarisationController = new SummarizationPresenter(articleViewModel);
        SummarizationInputBoundary summarizationInteractor = new SummarizationInteractor(summarisationController, summarizationDataAccessObject);

        return new SummarizationController(summarizationInteractor);
    }
}
