package interface_adapter;
import use_case.ArticleRetrievalInputData;
import use_case.ArticleRetrievalInputBoundary;

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
