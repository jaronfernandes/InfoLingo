package interface_adapter.translation;

import entity.Article;
import use_case.translation.TranslationInputBoundary;
import use_case.translation.TranslationInputData;

public class TranslationController {
    final TranslationInputBoundary translationInteractor;


    public TranslationController(TranslationInputBoundary translationInteractor) {
        this.translationInteractor = translationInteractor;
    }

    public void execute(String articleHeadline, String language) {
        // TODO: May need to edit this, instead of article might need something like Article_ID to locate the
        //  article object in the DAO.
        TranslationInputData TranslationInputData = new TranslationInputData(articleHeadline, language);
        translationInteractor.execute(TranslationInputData);
    }
}
