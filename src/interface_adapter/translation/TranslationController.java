package interface_adapter.translation;

import entity.Article;
import use_case.translation.TranslationInputBoundary;
import use_case.translation.TranslationInputData;

public class TranslationController {
    final TranslationInputBoundary translationInteractor;


    /**
     * This translation controller class is used to handle and process user input regarding
     * a string of text and the language they want to translate the text into.
     *
     * @param translationInteractor The translation interactor object for dependency injection.
     * @author Jaron Fernandes
     */
    public TranslationController(TranslationInputBoundary translationInteractor) {
        this.translationInteractor = translationInteractor;
    }

    /**
     * Executes the translation use case (by invoking the translation use case interactor).
     *
     * @param articleHeadline A string of text representing the article headline.
     * @param language A string of text in ISO 639 format representing the language to translate to.
     * @author Jaron Fernandes
     */
    public void execute(String articleHeadline, String language) {
        // TODO: May need to edit this, instead of article might need something like Article_ID to locate the
        //  article object in the DAO.
        TranslationInputData TranslationInputData = new TranslationInputData(articleHeadline, language);
        translationInteractor.execute(TranslationInputData);
    }
}
