package interface_adapter.article_retrieval;
import use_case.article_retrieval.ArticleRetrievalInputData;
import use_case.article_retrieval.ArticleRetrievalInputBoundary;

public class ArticleRetrievalController {
    final ArticleRetrievalInputBoundary articleRetrievalInteractor;


    public ArticleRetrievalController(ArticleRetrievalInputBoundary articleRetrievalInteractor) {
        this.articleRetrievalInteractor = articleRetrievalInteractor;
    }

    public void execute(String query){
        ArticleRetrievalInputData articleRetrievalInputData = new ArticleRetrievalInputData(query);
        articleRetrievalInteractor.execute(articleRetrievalInputData);
    }
}
