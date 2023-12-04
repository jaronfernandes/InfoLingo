package entity;
import java.util.List;

public class RankingFactory implements RankingFactoryInterface{

    public Ranking create(List<Article> articles, Preferences preferences) {
        return new Ranking(articles, preferences);
    }
}
