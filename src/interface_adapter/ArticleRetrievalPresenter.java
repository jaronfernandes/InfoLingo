package interface_adapter;

import use_case.ArticleRetrievalOutputBoundary;
import use_case.ArticleRetrievalOutputData;

public class ArticleRetrievalPresenter implements ArticleRetrievalOutputBoundary {
    @Override
    public void prepareSuccessView(ArticleRetrievalOutputData outputData) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
