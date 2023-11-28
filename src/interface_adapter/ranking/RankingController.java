package interface_adapter.ranking;

import entity.Article;
import entity.Preferences;
import use_case.ranking.RankingInputBoundary;
import use_case.ranking.RankingInputData;

import java.util.ArrayList;
import java.util.List;

public class RankingController {
    final RankingInputBoundary rankingInteractor;

    public RankingController(RankingInputBoundary rankingInteractor) {
        this.rankingInteractor = rankingInteractor;
    }

    public void execute(List<String> countries, String date){
        RankingInputData rankingInputData = new RankingInputData(countries, date);
        rankingInteractor.execute(rankingInputData);
    }
}
