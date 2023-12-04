package interface_adapter.translation;

import entity.Article;
import interface_adapter.ArticleState;
import interface_adapter.ArticleViewModel;
import interface_adapter.HomeState;
import interface_adapter.HomeViewModel;
import use_case.article_retrieval.ArticleRetrievalOutputBoundary;
import use_case.article_retrieval.ArticleRetrievalOutputData;
import use_case.translation.TranslationOutputBoundary;
import use_case.translation.TranslationOutputData;

import java.util.ArrayList;
import java.util.List;

public class TranslationPresenter implements TranslationOutputBoundary {
    private final ArticleViewModel articleViewModel;

    /**
     * This translation presenter class is used to handle the output data and update the view accordingly.
     *
     * @param articleViewModel The ArticleViewModel for dependency injection to update the ArticleView.
     * @author Jaron Fernandes
     */
    public TranslationPresenter(ArticleViewModel articleViewModel) {
        this.articleViewModel = articleViewModel;
    }

    @Override
    public void prepareSuccessView(TranslationOutputData outputData) {
        ArticleState currentArticleState = articleViewModel.getArticleState();
        currentArticleState.setTranslatedHeadline(outputData.getTranslatedHeadline());
        currentArticleState.setTranslatedContent(outputData.getTranslatedContent());
        currentArticleState.setTranslatedLanguage(outputData.getLanguage());

        articleViewModel.firePropertyChanged("translationArticleUpdate");
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println("you are such a failure");
        // TODO: - TRANSLATION - Hook up fail view!
        articleViewModel.firePropertyChanged("failedTranslationArticleUpdate");
    }
}
