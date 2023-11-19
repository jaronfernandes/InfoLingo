package use_case.grouping;
import entity.Article;
import java.util.ArrayList;

public class GroupingInputData {



    private ArrayList<Article> articles;


    public GroupingInputData(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }
}
