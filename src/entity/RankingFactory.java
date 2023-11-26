package entity;

import java.util.ArrayList;

public class RankingFactory implements RankingFactoryInterface{

    public Ranking create(ArrayList<Article> articles, Preferences preferences) {
        return new Ranking(articles, preferences);
    }
}
