package use_case.ranking.preferences;

import entity.Article;
import entity.Preferences;
import use_case.ranking.RankingInputData;

import java.util.List;

public class CountryPreferencesMatcher implements PreferencesMatcher{
    @Override
    public boolean matchPreferences(Article article, Preferences preferences, List<String> countries, String date, RankingInputData input) {
        return preferences.getCountries().contains(article.getCountry());
    }
}
