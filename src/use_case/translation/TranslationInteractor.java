package use_case.translation;

import entity.TranslatedArticle;

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
        TranslatedArticle article = translationDataAccessObject.translateArticle(inputData.getArticle(), inputData.getLanguage());

        // TODO: figure out what the fail view would be
        // TODO: remove this 1 == 0 nonsense (i dont want to activate fail view yet)
        if (1 == 0) {
            presenter.prepareFailView("Failed to translate the article!");
        }
        else {
            TranslationOutputData outputData = new TranslationOutputData(article.getContent());
            presenter.prepareSuccessView(outputData);
        }
    }
}
