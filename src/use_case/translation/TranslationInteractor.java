package use_case.translation;

import entity.TranslatedArticle;

import java.io.IOException;
import java.util.NoSuchElementException;

public class TranslationInteractor implements TranslationInputBoundary {
    private TranslationOutputBoundary presenter;
    private TranslateAPIDataAccessInterface translationDataAccessObject;

    public TranslationInteractor(TranslationOutputBoundary outputBoundary,
                                 TranslateAPIDataAccessInterface translationDataAccessObject) {
        this.translationDataAccessObject = translationDataAccessObject;
        this.presenter = outputBoundary;
    }

    @Override
    public void execute(TranslationInputData inputData) {
        try {
            TranslatedArticle article = translationDataAccessObject.translateArticle(
                    inputData.getHeadline(),
                    inputData.getLanguage()
            );

            TranslationOutputData outputData =
                    new TranslationOutputData(
                            article.getHeadline(),
                            article.getContent(),
                            article.getLanguage()
                    );

            presenter.prepareSuccessView(outputData);
        }
        // NullPointerException is thrown if something went wrong with the translation!
        catch (NoSuchElementException e) {
            presenter.prepareFailView("Could not find article with the given headline!");
        }
        catch (NullPointerException e) {
            presenter.prepareFailView("Failed to translate the article!");
        }
    }
}
