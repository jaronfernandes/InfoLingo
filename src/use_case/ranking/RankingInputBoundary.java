package use_case.ranking;

public interface RankingInputBoundary {
    /**
     * Executes the use-case. If successful, it will call the RankingPresenter to update the HomeView
     * based on the output data. If it failed, it calls the view to display the fail view to the user.
     *
     * @param inputData The RankingInputData needed through dependency injection from the controller.
     * @author Dominic Le
     */

    void execute(RankingInputData inputData);
}
