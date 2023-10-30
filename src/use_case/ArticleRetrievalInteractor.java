package use_case;

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

    }
}
