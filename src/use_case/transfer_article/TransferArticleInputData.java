package use_case.transfer_article;

import entity.Article;

import java.util.ArrayList;

public class TransferArticleInputData {



    private Article article;


    public TransferArticleInputData(Article article) {
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }
}
