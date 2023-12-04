package use_case.ranking.preferences;

import entity.Article;
import entity.Preferences;
import use_case.ranking.RankingInputData;

import java.util.List;

public class DatePreferencesMatcher implements PreferencesMatcher{
    @Override
    public boolean matchPreferences(Article article, Preferences preferences, List<String> countries, String date, RankingInputData input) {
        if (preferences.getDate().length() == 7) {
            if (preferences.getDate().equals(article.getPublishedAt().substring(0,7))){
                return true;

            }

        }
        if (preferences.getDate().length() == 4) {
            if (preferences.getDate().equals(article.getPublishedAt().substring(0,4))) {
                return true;
            }
        }

        return article.getPublishedAt().equals(preferences.getDate());
    }
}
