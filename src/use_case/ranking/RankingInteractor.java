package use_case.ranking;

import entity.*;
import use_case.ranking.preferences.DateCountryPreferencesMatcher;
import use_case.ranking.preferences.PreferencesMatcher;
import use_case.ranking.preferences.PreferencesMatcherFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RankingInteractor implements RankingInputBoundary {
    private RankingOutputBoundary presenter;
    private PreferencesMatcher preferencesMatcher;

    public RankingInteractor(RankingOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(RankingInputData inputData) {

        RankingFactory rankingFactory = new RankingFactory();
        List<Article> articles = inputData.getArticles();
        PreferencesFactory preferencesFactory = new PreferencesFactory();
        Preferences preferences = preferencesFactory.create(inputData.getCountries(),inputData.getDate());
        List<String> countries = inputData.getCountries();
        String date = inputData.getDate();
        ArrayList<Article> ranking = new ArrayList<>();

        if ((inputData.getDate().compareTo("YYYY-MM-DD") == 0 || inputData.getDate().compareTo("") == 0)  && inputData.getCountries().isEmpty()) {
            System.out.println("No preferences entered.");
            presenter.prepareSuccessView(new RankingOutputData(rankingFactory.create(articles, preferences)));
            return;
        }

        preferencesMatcher = new PreferencesMatcherFactory().createPreferencesMatcherBasedOnConditions(inputData);
        for (Article article: articles){
            if (preferencesMatcher.matchPreferences(article, preferences, countries, date, inputData)){
                ranking.add(article);
            }
        }
        if (ranking.isEmpty()) {
            presenter.prepareFailView("Nothing matches your preferences. Try reducing filter!");
        }
        else {
            RankingOutputData outputData = new RankingOutputData(rankingFactory.create(ranking, preferences));
            presenter.prepareSuccessView(outputData);
            }
        }
    }


