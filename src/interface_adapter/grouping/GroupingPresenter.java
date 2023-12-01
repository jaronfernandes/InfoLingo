package interface_adapter.grouping;

import entity.Article;
import entity.Grouping;
import interface_adapter.GroupingViewModel;
import interface_adapter.GroupingState;
import use_case.grouping.GroupingOutputBoundary;
import use_case.grouping.GroupingOutputData;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GroupingPresenter implements GroupingOutputBoundary {

    private final GroupingViewModel groupingViewModel;

    public GroupingPresenter(GroupingViewModel groupingViewModel) {
        this.groupingViewModel = groupingViewModel;
    }
    @Override
    public void prepareSuccessView(GroupingOutputData outputData) {
        System.out.println("success");
        GroupingState currentGroupingState = groupingViewModel.getGroupingState();
        currentGroupingState.setGroupings(outputData.getGroupings());

        // Extract headlines.
        List<String> headlines = new ArrayList<>();
        DefaultListModel<String> headlinesModel = currentGroupingState.getHeadlinesModel();
        headlinesModel.clear();
        for (Grouping grouping:
                currentGroupingState.getGroupings()) {
            String headline = grouping.getHeadline();
            headlines.add(headline);
            headlinesModel.addElement(headline);
        }

        // Set headlines.
        currentGroupingState.setHeadlines(headlines);
        groupingViewModel.firePropertyChanged("Grouping");


    }

    @Override
    public void prepareFailView(String error) {
        System.out.println("failed");
        GroupingState currentGroupingState = groupingViewModel.getGroupingState();
        currentGroupingState.setGroupingError(error);
        groupingViewModel.firePropertyChanged("failedGrouping");
    }
}
