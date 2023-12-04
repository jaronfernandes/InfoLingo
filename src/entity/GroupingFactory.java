package entity;
import java.util.ArrayList;

public class GroupingFactory implements GroupingFactoryInterface{


    /**
     * Factory that creates groups of similar articles
     * @param articles an ArrayList of Articles to be added to a Grouping
     * @return a new Grouping
     * @author Seth
     */
    @Override
    public Grouping create(ArrayList<Article> articles) {
        return new Grouping(articles);
    }
}
