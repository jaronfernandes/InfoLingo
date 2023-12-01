package interface_adapter.transfer_article;


import entity.Article;
import use_case.transfer_article.TransferArticleInputBoundary;
import use_case.transfer_article.TransferArticleInputData;

public class TransferArticleController {
    final TransferArticleInputBoundary transferArticleInteractor;


    public TransferArticleController(TransferArticleInputBoundary transferArticleInteractor) {
        this.transferArticleInteractor = transferArticleInteractor;
    }

    public void execute(Article article){
        TransferArticleInputData articleRetrievalInputData = new TransferArticleInputData(article);
        transferArticleInteractor.execute(articleRetrievalInputData);
    }
}
