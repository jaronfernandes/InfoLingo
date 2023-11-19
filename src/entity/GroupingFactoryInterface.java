package entity;
import java.util.ArrayList;

public interface GroupingFactoryInterface {

    Grouping create(ArrayList<Article> articles);
}
