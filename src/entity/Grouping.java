package entity;
import java.util.ArrayList;

public class Grouping {

    private final ArrayList<Article> articles;

    public Grouping(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public String getHeadline() {
        return this.articles.get(0).getHeadline();
    }
}
