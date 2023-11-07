package app;

import interface_adapter.ArticleViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.translation.TranslationController;
import interface_adapter.translation.TranslationPresenter;
import interface_adapter.translation.TranslationPresenter;
import use_case.translation.TranslateAPIDataAccessInterface;
import use_case.translation.TranslationInputBoundary;
import use_case.translation.TranslationInteractor;
import use_case.translation.TranslationOutputBoundary;
import view.ArticleView;

public class TranslationUseCaseFactory {

    public static ArticleView create(ViewManagerModel viewManagerModel, ArticleViewModel articleViewModel, TranslateAPIDataAccessInterface translationDataAccessObject) {
        TranslationController translationController = createTranslationUseCase(viewManagerModel, articleViewModel, translationDataAccessObject);
        return new ArticleView(translationController, articleViewModel);
    }

    private static TranslationController createTranslationUseCase(ViewManagerModel viewManagerModel, ArticleViewModel articleViewModel, TranslateAPIDataAccessInterface translationDataAccessObject) {
        TranslationOutputBoundary translationOutputBoundary = new TranslationPresenter(articleViewModel);
        TranslationInputBoundary translationInputBoundary = new TranslationInteractor(translationOutputBoundary, translationDataAccessObject);

        return new TranslationController(translationInputBoundary);
    }
}
