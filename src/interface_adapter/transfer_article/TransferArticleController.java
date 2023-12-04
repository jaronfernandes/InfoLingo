package interface_adapter.transfer_article;


import entity.Article;
import use_case.transfer_article.TransferArticleInputBoundary;
import use_case.transfer_article.TransferArticleInputData;

/**
 * Packages and passes data relevant to navigation to Interactor.
 * @author Jaiz Jeeson
 * @author Jaron Fernandes
 */
public class TransferArticleController {
    final TransferArticleInputBoundary transferArticleInteractor;

    public TransferArticleController(TransferArticleInputBoundary transferArticleInteractor) {
        this.transferArticleInteractor = transferArticleInteractor;
    }

    /**
     * Method that passes Article object to be displayed to interactor.
     * @param article Article object that is displayed in manufactured ArticleView.
     */
    public void execute(Article article) {
        TransferArticleInputData articleRetrievalInputData = new TransferArticleInputData(article);
        transferArticleInteractor.execute(articleRetrievalInputData);
    }

    /**
     * Overloaded the execute method to accept no input in case an article doesn't exist.
     */
    public void execute() {
        TransferArticleInputData articleRetrievalInputData = new TransferArticleInputData();
        transferArticleInteractor.execute(articleRetrievalInputData);
    }
}
