package use_case.article_retrieval;

import entity.Article;

import java.util.List;

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
        List<Article> articles = articleRetrievalDataAccessObject.getArticles(inputData.getQuery());

        if (articles.isEmpty()) {
            presenter.prepareFailView("Failed to retrieve any articles!");
        }
        else {
            ArticleRetrievalOutputData outputData = new ArticleRetrievalOutputData(articles);
            presenter.prepareSuccessView(outputData);
        }
    }
}
