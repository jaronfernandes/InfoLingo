package use_case.grouping;

import entity.Grouping;

import java.util.ArrayList;

public class GroupingOutputData {

    ArrayList<Grouping> groupings;

    public GroupingOutputData(ArrayList<Grouping> groupings) {
        this.groupings = groupings;
    }

    public ArrayList<Grouping> getGroupings() {
        return groupings;
    }

    public void setGroupings(ArrayList<Grouping> groupings) {
        this.groupings = groupings;
    }
}
