package use_case.article_retrieval;

import entity.Article;

import java.util.List;

public class ArticleRetrievalInteractor implements ArticleRetrievalInputBoundary {
    private ArticleRetrievalOutputBoundary presenter;
    private ArticleRetrievalDataAccessInterface articleRetrievalDataAccessObject;

    /**
     * The Article Retrieval interactor is used to handle the Article Retrieval use case,
     * by calling the API methods to get a list of articles, and then call the presenter to update the view
     * so that it displays them.
     *
     * @param outputBoundary The Article Retrieval Presenter object needed through dependency injection.
     * @param articleRetrievalDataAccessObject The Article Retrieval DAO.
     * @author Seth Blatt, Jaiz Jeeson, Jaron Fernandes
     */
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
