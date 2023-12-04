package use_case.transfer_article;

import entity.Article;
import entity.Grouping;

import java.util.ArrayList;
import java.util.List;

/**
 * Input data for summarisation.
 * @author Jaiz Jeeson
 * @author Jaron Fernandes
 */
public class TransferArticleOutputData {
    Article article;
    public TransferArticleOutputData(Article article) {
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }
}
