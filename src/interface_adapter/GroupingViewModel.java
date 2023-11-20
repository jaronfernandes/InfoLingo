package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GroupingViewModel extends ViewModel{

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private GroupingState groupingState;

    public GroupingViewModel(GroupingState groupingState) {
        super("grouping");
        this.groupingState = groupingState;
    }

    public GroupingState getGroupingState() {
        return groupingState;
    }

    public void setGroupingState(GroupingState groupingState) {
        this.groupingState = groupingState;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.groupingState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
