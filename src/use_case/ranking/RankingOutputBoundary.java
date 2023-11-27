package use_case.ranking;

public interface RankingOutputBoundary {

    void prepareSuccessView(RankingOutputData outputData);

    void prepareFailView(String error);
}
