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

    public TranslationPresenter(ArticleViewModel articleViewModel) {
        this.articleViewModel = articleViewModel;
    }

    @Override
    public void prepareSuccessView(TranslationOutputData outputData) {
        // TODO: - TRANSLATION - Hook up success view!
        // System.out.println("HIII YAYYY");

        ArticleState currentArticleState = articleViewModel.getArticleState();
        currentArticleState.setTranslatedHeadline(outputData.getTranslatedHeadline());
        currentArticleState.setTranslatedContent(outputData.getTranslatedContent());
        currentArticleState.setTranslatedLanguage(outputData.getLanguage());

        articleViewModel.firePropertyChanged("translationArticleUpdate");
//        System.out.println("success");
//        HomeState currentHomeState = homeViewModel.getHomeState();
//        currentHomeState.setArticles(outputData.getArticles());
//
//        // Extract headlines.
//        List<String> headlines = new ArrayList<>();
//        for (Article article:
//             currentHomeState.getArticles()) {
//            headlines.add(article.getHeadline());
//        }
//
//        // Set headlines.
//        currentHomeState.setHeadlines(headlines);
//        homeViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println("you are such a failure");
        // TODO: - TRANSLATION - Hook up fail view!
        articleViewModel.firePropertyChanged("failedTranslationArticleUpdate");
    }
}
