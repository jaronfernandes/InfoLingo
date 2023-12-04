package use_case.ranking.preferences;

import entity.Article;
import entity.Preferences;
import use_case.ranking.RankingInputData;

import java.util.List;

public interface PreferencesMatcher {
    boolean matchPreferences(Article article, Preferences preferences, List<String> countries, String date, RankingInputData input);
}
