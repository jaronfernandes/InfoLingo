package entity;
import java.util.ArrayList;

public class Grouping {

    private final ArrayList<Article> articles;

    /**
     * Groups of similar articles
     * @param articles
     * @author Seth
     */
    public Grouping(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public String getHeadline() {
        return this.articles.get(0).getHeadline();
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }
}
