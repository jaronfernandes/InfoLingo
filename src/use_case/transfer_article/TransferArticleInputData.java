package use_case.transfer_article;

import entity.Article;

public class TransferArticleInputData {
    private Article article = null;

    public TransferArticleInputData() {
    }

    public TransferArticleInputData(Article article) {
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }
}
