package interface_adapter.ranking;

import interface_adapter.RankingState;
import interface_adapter.RankingViewModel;
import use_case.ranking.RankingOutputBoundary;
import use_case.ranking.RankingOutputData;

public class RankingPresenter implements RankingOutputBoundary {

    private final RankingViewModel rankingViewmodel;

    public RankingPresenter(RankingViewModel rankingViewmodel) {
        this.rankingViewmodel = rankingViewmodel;
    }

    @Override
    public void prepareSuccessView(RankingOutputData outputData) {
        RankingState currentRankingState = rankingViewmodel.getRankingState();

        System.out.println("Presenter: " + outputData.getRanking());

        currentRankingState.setRankings(outputData.getRanking());

        rankingViewmodel.firePropertyChanged("rankingUpdate");
    }

    @Override
    public void prepareFailView(String error) {
        RankingState currentRankingState = rankingViewmodel.getRankingState();
        currentRankingState.setRankingError(error);
        rankingViewmodel.firePropertyChanged("rankingUpdate");

    }
}
