package use_case.ranking.preferences;

import entity.Article;
import entity.Preferences;
import use_case.ranking.RankingInputData;

import java.util.List;

public class DateCountryPreferencesMatcher implements PreferencesMatcher{
    @Override
    public boolean matchPreferences(Article article, Preferences preferences, List<String> countries, String date, RankingInputData input) {
        return new CountryPreferencesMatcher().matchPreferences(article, preferences, countries, date, input)
                && new DatePreferencesMatcher().matchPreferences(article, preferences, countries, date, input);
    }

}
