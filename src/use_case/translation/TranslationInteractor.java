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
        try {
            TranslatedArticle article = translationDataAccessObject.translateArticle(inputData.getArticle(), inputData.getLanguage());

//            System.out.println(article.getHeadline());
//            System.out.println(article.getContent());
//            System.out.println(article.getLanguage());

            TranslationOutputData outputData =
                    new TranslationOutputData(
                            article.getHeadline(),
                            article.getContent(),
                            article.getLanguage()
                    );

            presenter.prepareSuccessView(outputData);
        }
        // NullPointerException is thrown if something went wrong with the translation!
        catch (NullPointerException e) {
            presenter.prepareFailView("Failed to translate the article!");
        }
    }
}
