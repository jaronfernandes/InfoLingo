package entity;
import java.util.ArrayList;

public class GroupingFactory implements GroupingFactoryInterface{


    @Override
    public Grouping create(ArrayList<Article> articles) {
        return new Grouping(articles);
    }
}
