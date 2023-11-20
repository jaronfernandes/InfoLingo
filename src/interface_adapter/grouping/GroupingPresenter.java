package interface_adapter.grouping;

import use_case.grouping.GroupingOutputBoundary;
import use_case.grouping.GroupingOutputData;

public class GroupingPresenter implements GroupingOutputBoundary {
    @Override
    public void prepareSuccessView(GroupingOutputData outputData) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
