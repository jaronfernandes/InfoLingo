package interface_adapter.summarization;

import entity.Article;
import interface_adapter.ArticleState;
import interface_adapter.ArticleViewModel;
import use_case.summarization.SummarizationOutputBoundary;
import use_case.summarization.SummarizationOutputData;

import java.util.ArrayList;
import java.util.List;

/**
 * Presenter class that controls View Model state.
 * Javadocs for methods delegated to interface.
 * @author Jaiz Jeeson
 */
public class SummarizationPresenter implements SummarizationOutputBoundary {
    private final ArticleViewModel articleViewModel;

    public SummarizationPresenter(ArticleViewModel articleViewModel) {
        this.articleViewModel = articleViewModel;
    }

    @Override
    public void prepareSuccessView(SummarizationOutputData outputData) {
        ArticleState currentArticleState = articleViewModel.getArticleState();

        System.out.println("Presenter: " + outputData.getSummarizedText());

        currentArticleState.setSummarisedContent(outputData.getSummarizedText());

        articleViewModel.firePropertyChanged("summarizationUpdate");
    }

    @Override
    public void prepareFailView(String error) {
        ArticleState currentArticleState = articleViewModel.getArticleState();
        currentArticleState.setSummarisationError(error);
        articleViewModel.firePropertyChanged("summarizationUpdate");
    }
}
