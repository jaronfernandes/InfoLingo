package use_case.ranking;

import entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RankingInteractor implements RankingInputBoundary {
    private RankingOutputBoundary presenter;

    public RankingInteractor(RankingOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(RankingInputData inputData) {
        if (Objects.equals(inputData.getDate(), "YYYY-MM-DD") && inputData.getCountries().isEmpty()) {
            System.out.println("No preferences entered.");
            return;
        }

        RankingFactory rankingFactory = new RankingFactory();
        List<Article> articles = inputData.getArticles();
        PreferencesFactory preferencesFactory = new PreferencesFactory();
        Preferences preferences = preferencesFactory.create(inputData.getCountries(),inputData.getDate());
        List<String> countries = inputData.getCountries();
        String date = inputData.getDate();

        ArrayList<Article> ranking = new ArrayList<>();
        for (Article article: articles){
            if(matchPreferences(article, preferences, countries, date, inputData)) {
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

    boolean matchPreferences(Article article, Preferences preferences, List<String> countries, String date,
                             RankingInputData input) {
        if (!input.getCountries().isEmpty() && input.getDate() != "YYYY-MM-DD") {
            if (Objects.equals(article.getPublishedAt(), preferences.getDate()) &&
                    preferences.getCountries().contains(article.getCountry())) {
                return true;
            }
        }
        if (Objects.equals(input.getDate(), "YYYY-MM-DD")) {
            if (preferences.getCountries().contains(article.getCountry())) {
                return true;
            }
        }
        if (input.getCountries().isEmpty()) {
            if (preferences.getCountries().contains(article.getCountry())) {
                return true;
            }
        }
        return false;
    }

}

//package use_case.ranking;
//
//import entity.*;
//
//import java.util.ArrayList;
//
//public class RankingInteractor implements RankingInputBoundary {
//    private RankingOutputBoundary presenter;
//
//    public RankingInteractor(RankingOutputBoundary presenter) {
//        this.presenter = presenter;
//    }
//
//    @Override
//    public void execute(RankingInputData inputData) {
//        RankingFactory rankingFactory = new RankingFactory();
//        ArrayList<Article> articles = inputData.getArticles();
//        PreferencesFactory preferencesFactory = new PreferencesFactory();
//        Preferences preferences = inputData.getPreferences();
//
//        ArrayList<Article> ranking = new ArrayList<>();
//        for (Article article: articles){
//            if(matchPreferences(article, preferences)){
//                ranking.add(article);
//            }
//        }
//        if (ranking.isEmpty()) {
//            presenter.prepareFailView("Nothing matches your preferences. Try reducing filter!");
//        }
//        else {
//            RankingOutputData outputData = new RankingOutputData(ranking);
//            presenter.prepareSuccessView(outputData);
//        }
//    }
//
//    boolean matchPreferences(Article article, Preferences preferences){
//        if (article.getDate == preferences.getDate() &&
//                article.getKeywords == preferences.getKeywords() &&
//                article.getDate() == preferences.getDate() &&
//                article.getCategories() == preferences.getCategories()) {
//        return true;
//        }
//        return false;
//    }
//}
