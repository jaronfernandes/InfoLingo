package entity;
import java.util.ArrayList;

public interface RankingFactoryInterface {

    Ranking create(ArrayList<Article> articles, Preferences preferences);
}
