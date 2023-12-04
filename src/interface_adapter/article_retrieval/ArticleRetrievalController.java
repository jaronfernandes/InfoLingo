package interface_adapter.article_retrieval;
import use_case.article_retrieval.ArticleRetrievalInputData;
import use_case.article_retrieval.ArticleRetrievalInputBoundary;

public class ArticleRetrievalController {
    final ArticleRetrievalInputBoundary articleRetrievalInteractor;


    /**
     * This article retrieval controller class is used to handle and process user input regarding
     * a string of text representing the query to find articles with.
     *
     * @param articleRetrievalInteractor The article retrieval interactor object for dependency injection.
     * @author Seth Blatt
     */
    public ArticleRetrievalController(ArticleRetrievalInputBoundary articleRetrievalInteractor) {
        this.articleRetrievalInteractor = articleRetrievalInteractor;
    }

    /**
     * Executes the article retrieval use case (by invoking the article retrieval use case interactor).
     *
     * @param query A string of text representing the search entry.
     * @author Seth Blatt
     */
    public void execute(String query){
        ArticleRetrievalInputData articleRetrievalInputData = new ArticleRetrievalInputData(query);
        articleRetrievalInteractor.execute(articleRetrievalInputData);
    }
}
