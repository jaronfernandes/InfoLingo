package use_case.article_retrieval;

public class ArticleRetrievalInputData {

    private String query;

    public ArticleRetrievalInputData(String query) {
        this.query = query;
    }

    /**
     * @return query The search query
     * @author Jaron Fernandes
     */
    public String getQuery() {
        return query;
    }

}
