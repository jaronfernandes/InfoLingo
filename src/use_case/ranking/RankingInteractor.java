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
        if (inputData.getDate().compareTo("YYYY-MM-DD") == 0 && inputData.getCountries().isEmpty()) {
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
                //System.out.println("added");
            }
        }
        if (ranking.isEmpty()) {
            System.out.println("nooo");
            presenter.prepareFailView("Nothing matches your preferences. Try reducing filter!");
        }
        else {
            RankingOutputData outputData = new RankingOutputData(rankingFactory.create(ranking, preferences));
            presenter.prepareSuccessView(outputData);
            //testing
            /*for (Article article: ranking){
                System.out.println("YAY");
                System.out.println(article.getHeadline());
            }*/
        }
    }

    boolean matchPreferences(Article article, Preferences preferences, List<String> countries, String date,
                             RankingInputData input) {
        if (!input.getCountries().isEmpty() && !input.getDate().equals("YYYY-MM-DD")) {
            if (article.getPublishedAt().equals(preferences.getDate()) &&
                    preferences.getCountries().contains(article.getCountry())) {
                System.out.println("wooh");
                return true;
            }
        }
        if (input.getDate().equals("YYYY-MM-DD")) {
            // System.out.println("wooh?");
            // System.out.println(article.getCountry());
            // System.out.println("preferences.countries:");
            for (String country: preferences.getCountries()){
                // System.out.println(country);
            }
            if (preferences.getCountries().contains(article.getCountry())) {
                // System.out.println("wooh1");
                return true;
            }
        }
        if (input.getCountries().isEmpty()) {

            if (preferences.getDate().length() == 7) {
                // System.out.println("7");
                // System.out.println(article.getPublishedAt().substring(0,7));
                if (preferences.getDate().equals(article.getPublishedAt().substring(0,7))){
                    System.out.println("woo");
                    return true;

                }

            }
            if (preferences.getDate().length() == 4) {
                if (preferences.getDate().equals(article.getPublishedAt().substring(0,4))) {
                    // System.out.println("woooo");
                    return true;
                }
            }
            /*
            System.out.println("wooh??");
            System.out.println(article.getPublishedAt());
            System.out.println("preferences.date:");
            System.out.println(preferences.getDate());
             */
            if (article.getPublishedAt().equals(preferences.getDate())) {
                // System.out.println("wooh2");
                // System.out.println((article.getPublishedAt()).equals(preferences.getDate()));
                return true;
            }
        }
        // System.out.println("nooo1");
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
