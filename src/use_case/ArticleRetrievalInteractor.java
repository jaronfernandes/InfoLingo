package use_case;

import data_access.APIDataAccessObject;
import entity.Article;

import java.util.ArrayList;

public class ArticleRetrievalInteractor implements ArticleRetrievalInputBoundary {
    private ArticleRetrievalInputData inputData;
    private ArticleRetrievalOutputBoundary presenter;
    private APIDataAccessObject articleRetrievalDataAccessObject;

    public ArticleRetrievalInteractor(ArticleRetrievalInputData inputData, ArticleRetrievalOutputBoundary outputBoundary,
                                      APIDataAccessObject articleRetrievalDataAccessObject) {
        this.articleRetrievalDataAccessObject = articleRetrievalDataAccessObject;
        this.inputData = inputData;
        this.presenter = outputBoundary;
    }

    @Override
    public void execute(ArticleRetrievalInputData inputData) {
        try {
            Article[] articles = articleRetrievalDataAccessObject.getSampleNews();

            ArticleRetrievalOutputData outputData = new ArticleRetrievalOutputData(articles);
            presenter.prepareSuccessView(outputData);
        }
        catch (Exception e) {
            presenter.prepareFailView(e.toString());
            System.out.println("what a failure.");
        }
    }
}
