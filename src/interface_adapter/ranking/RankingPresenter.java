package interface_adapter.ranking;

import entity.Article;
import interface_adapter.HomeState;
import interface_adapter.HomeViewModel;
import use_case.ranking.RankingOutputBoundary;
import use_case.ranking.RankingOutputData;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class RankingPresenter implements RankingOutputBoundary {

    private final HomeViewModel homeViewModel;

    public RankingPresenter(HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView(RankingOutputData outputData) {

        System.out.println("Presenter: " + outputData.getRanking());

        HomeState currentHomeState = homeViewModel.getHomeState();

        currentHomeState.setArticles(outputData.getRanking());

        // Get headlines
        List<String> headlines = new ArrayList<>();
        DefaultListModel<String> headlinesModel = currentHomeState.getHeadlinesModel();
        headlinesModel.clear();
        for (Article article:
                currentHomeState.getArticles()) {
            String headline = article.getHeadline();
            headlines.add(headline);
            headlinesModel.addElement(headline);
        }
        // Set headlines
        currentHomeState.setHeadlines(headlines);
        homeViewModel.firePropertyChanged("rankingUpdate");
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println("failed:" + error);
        HomeState currentHomeState = homeViewModel.getHomeState();
        currentHomeState.setArticleRetrievalError(error);
        homeViewModel.firePropertyChanged("rankingUpdate", error);
    }
}
