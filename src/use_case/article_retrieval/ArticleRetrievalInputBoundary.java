package use_case.article_retrieval;

public interface ArticleRetrievalInputBoundary {
    /**
     * Executes the use-case. If successful, it will call the ArticleRetrievalPresenter to update the HomeView
     * based on the output data. If it failed, it calls the view to display the fail view to the user.
     *
     * @param inputData The ArticleRetrievalInputData needed through dependency injection from the controller.
     * @author Jaron Fernandes, Seth Blatt
     */
    void execute(ArticleRetrievalInputData inputData);
}
