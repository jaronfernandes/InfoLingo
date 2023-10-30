package use_case;

import data_access.APIDataAccessObject;
import entity.Article;

import java.util.ArrayList;

public class ArticleRetrievalInteractor implements ArticleRetrievalInputBoundary {
    private ArticleRetrievalOutputBoundary presenter;
    private ArticleRetrievalDataAccessInterface articleRetrievalDataAccessObject;

    public ArticleRetrievalInteractor(ArticleRetrievalOutputBoundary outputBoundary,
                                      ArticleRetrievalDataAccessInterface articleRetrievalDataAccessObject) {
        this.articleRetrievalDataAccessObject = articleRetrievalDataAccessObject;
        this.presenter = outputBoundary;
    }

    @Override
    public void execute(ArticleRetrievalInputData inputData) {
        Article[] articles = articleRetrievalDataAccessObject.getSampleNews(inputData.getQuery());

        if (articles.length == 0) {
            presenter.prepareFailView("Failed to retrieve any articles!");
        }
        else {
            ArticleRetrievalOutputData outputData = new ArticleRetrievalOutputData(articles);
            presenter.prepareSuccessView(outputData);
        }
    }
}
