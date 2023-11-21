package interface_adapter;

import entity.Grouping;

import java.util.ArrayList;
import java.util.List;

public class GroupingState {
    private List<Grouping> groupings;
    private List<String> headlines;
    private String groupingError;

    public GroupingState(List<Grouping> groupings) {
        this.groupings = groupings;
        this.groupingError = "";
        this.headlines = new ArrayList<>();
        for (Grouping grouping : groupings) {
            this.headlines.add(grouping.getHeadline());
        }
    }

    public List<Grouping> getGroupings() {
        return groupings;
    }

    public void setGroupings(List<Grouping> groupings) {
        this.groupings = groupings;
    }

    public List<String> getHeadlines() {
        return headlines;
    }

    public void setHeadlines(List<String> headlines) {
        this.headlines = headlines;
    }

    public String getGroupingError() {
        return groupingError;
    }

    public void setGroupingError(String groupingError) {
        this.groupingError = groupingError;
    }
}
