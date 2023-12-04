package entity;
import java.util.ArrayList;
import java.util.List;

public interface RankingFactoryInterface {

    Ranking create(List<Article> articles, Preferences preferences);
}
