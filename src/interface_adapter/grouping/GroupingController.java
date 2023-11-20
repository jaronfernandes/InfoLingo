package interface_adapter.grouping;

import entity.Article;
import use_case.grouping.GroupingInputBoundary;
import use_case.grouping.GroupingInputData;

import java.util.ArrayList;

public class GroupingController {
    final GroupingInputBoundary groupingInteractor;


    public GroupingController(GroupingInputBoundary groupingInteractor) {
        this.groupingInteractor = groupingInteractor;
    }

    public void execute(ArrayList<Article> articles){
        GroupingInputData groupingInputData = new GroupingInputData(articles);
        groupingInteractor.execute(groupingInputData);
    }
}
