package use_case.ranking.preferences;
import use_case.ranking.RankingInputData;

public class PreferencesMatcherFactory {
    public PreferencesMatcher createPreferencesMatcherBasedOnConditions(RankingInputData inputData) {
        if (!inputData.getCountries().isEmpty() && (!inputData.getDate().equals("YYYY-MM-DD"))) {
            return new DateCountryPreferencesMatcher();
        }
        if (inputData.getDate().equals("YYYY-MM-DD")) {
            return new CountryPreferencesMatcher();
        }
        if (inputData.getCountries().isEmpty()) {
            return new DatePreferencesMatcher();
        }
        return null;
    }
}