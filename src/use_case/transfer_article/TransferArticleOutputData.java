package use_case.transfer_article;

import entity.Article;
import entity.Grouping;

import java.util.ArrayList;
import java.util.List;

public class TransferArticleOutputData {

    Article article = null;
    String transferArticleError = null;

    public TransferArticleOutputData(Article article) {
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getTransferArticleError() {
        return transferArticleError;
    }

    public void setTransferArticleError(String transferArticleError) {
        this.transferArticleError = transferArticleError;
    }
}
