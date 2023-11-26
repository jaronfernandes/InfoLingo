package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HomeViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private HomeState homeState;

    public HomeViewModel(HomeState homeState) {
        super("home");
        this.homeState = homeState;
    }

    public HomeState getHomeState() {
        return homeState;
    }

    public void setHomeState(HomeState homeState) {
        this.homeState = homeState;
    }

    @Override
    public void firePropertyChanged(String eventName) {
        support.firePropertyChange("state", null, this.homeState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
