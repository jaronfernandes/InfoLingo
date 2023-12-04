package use_case.translation;

import entity.TranslatedArticle;

import java.io.IOException;
import java.util.NoSuchElementException;

public class TranslationInteractor implements TranslationInputBoundary {
    private TranslationOutputBoundary presenter;
    private TranslateAPIDataAccessInterface translationDataAccessObject;

    /**
     * The translation interactor is used to handle the translation use case, by calling the API methods to access articles
     * and to translate them through the API.
     *
     * @param outputBoundary The translation presenter object needed through dependency injection.
     * @param translationDataAccessObject The translation DAO.
     * @author Jaron Fernandes
     */
    public TranslationInteractor(TranslationOutputBoundary outputBoundary,
                                 TranslateAPIDataAccessInterface translationDataAccessObject) {
        this.translationDataAccessObject = translationDataAccessObject;
        this.presenter = outputBoundary;
    }

    /**
     * Executes the use-case. If successful, it will call the TranslationPresenter to update the ArticleView
     * based on the output data. If it failed, it calls the view to display the fail view to the user.
     *
     * @param inputData The TranslationInputData needed through dependency injection from the controller.
     * @author Jaron Fernandes
     */
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
