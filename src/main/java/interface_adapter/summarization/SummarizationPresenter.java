package interface_adapter.summarization;

import entity.Article;
import interface_adapter.ArticleState;
import interface_adapter.ArticleViewModel;
import use_case.summarization.SummarizationOutputBoundary;
import use_case.summarization.SummarizationOutputData;

import java.util.ArrayList;
import java.util.List;

public class SummarizationPresenter implements SummarizationOutputBoundary {
    private final ArticleViewModel articleViewModel;

    public SummarizationPresenter(ArticleViewModel homeViewModel) {
        this.articleViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView(SummarizationOutputData outputData) {
        ArticleState currentArticleState = articleViewModel.getArticleState();

        System.out.println("Presenter: " + outputData.getSummarizedText());

        currentArticleState.setSummarisedContent(outputData.getSummarizedText());

        articleViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        ArticleState currentArticleState = articleViewModel.getArticleState();
        currentArticleState.setSummarisationError(error);
        articleViewModel.firePropertyChanged();
    }
}
