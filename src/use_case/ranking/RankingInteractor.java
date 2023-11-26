package use_case.ranking;

import entity.*;

import java.util.ArrayList;

public class RankingInteractor implements RankingInputBoundary {
    private RankingOutputBoundary presenter;

    public RankingInteractor(RankingOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(RankingInputData inputData) {
        RankingFactory rankingFactory = new RankingFactory();
        ArrayList<Article> articles = inputData.getArticles();
        PreferencesFactory preferencesFactory = new PreferencesFactory();
        Preferences preferences = inputData.getPreferences();

        ArrayList<Article> ranking = new ArrayList<>();
        for (Article article: articles){
            if(matchPreferences(article, preferences)) {
                ranking.add(article);
            }
        }
        if (ranking.isEmpty()) {
            presenter.prepareFailView("Nothing matches your preferences. Try reducing filter!");
        }
        else {
            RankingOutputData outputData = new RankingOutputData(ranking);
            presenter.prepareSuccessView(outputData);
        }
    }

    boolean matchPreferences(Article article, Preferences preferences){
        if (article.getPublishedAt() == preferences.getDate() &&
                 preferences.getCountries().contains(article.getCountry())){
        return true;
        }
        return false;
    }
}
