package app;

import interface_adapter.ArticleViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.summarization.SummarizationController;
import interface_adapter.summarization.SummarizationPresenter;
import interface_adapter.translation.TranslationController;
import interface_adapter.translation.TranslationPresenter;
import interface_adapter.translation.TranslationPresenter;
import use_case.summarization.SummarizationDataAccessInterface;
import use_case.summarization.SummarizationInputBoundary;
import use_case.summarization.SummarizationInteractor;
import use_case.summarization.SummarizationOutputBoundary;
import use_case.translation.TranslateAPIDataAccessInterface;
import use_case.translation.TranslationInputBoundary;
import use_case.translation.TranslationInteractor;
import use_case.translation.TranslationOutputBoundary;
import view.ArticleView;

public class TranslationUseCaseFactory {

    /**
     *
     * @param viewManagerModel The ViewManagerModel
     * @param articleViewModel The ArticleViewModel
     * @param translationDataAccessObject The TranslateAPIDataAccessInterface
     * @param summarizationDataAccessObject The SummarizationDataAccessInterface
     * @return a new ArticleView
     * @author Jaron Fernandes
     */
    public static ArticleView create(ViewManagerModel viewManagerModel,
                                     ArticleViewModel articleViewModel,
                                     TranslateAPIDataAccessInterface translationDataAccessObject,
                                     SummarizationDataAccessInterface summarizationDataAccessObject) {
        TranslationController translationController = createTranslationUseCase(viewManagerModel, articleViewModel, translationDataAccessObject);
        SummarizationController summarizationController = createSummarisationUseCase(viewManagerModel, articleViewModel, summarizationDataAccessObject);
        return new ArticleView(translationController, articleViewModel, summarizationController);
    }

    private static TranslationController createTranslationUseCase(ViewManagerModel viewManagerModel, ArticleViewModel articleViewModel, TranslateAPIDataAccessInterface translationDataAccessObject) {
        TranslationOutputBoundary translationOutputBoundary = new TranslationPresenter(articleViewModel);
        TranslationInputBoundary translationInputBoundary = new TranslationInteractor(translationOutputBoundary, translationDataAccessObject);

        return new TranslationController(translationInputBoundary);
    }

    private static SummarizationController createSummarisationUseCase(ViewManagerModel viewManagerModel, ArticleViewModel articleViewModel, SummarizationDataAccessInterface summarizationDataAccessObject) {
        SummarizationOutputBoundary summarisationController = new SummarizationPresenter(articleViewModel);
        SummarizationInputBoundary summarizationInteractor = new SummarizationInteractor(summarisationController, summarizationDataAccessObject);

        return new SummarizationController(summarizationInteractor);
    }
}
