package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RankingViewModel extends ViewModel{
    private RankingState rankingState = new RankingState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public RankingViewModel(RankingState rankingState) {
        super("ranking");
        this.rankingState = rankingState;
    }

    public RankingState getRankingState() {
        return rankingState;
    }

    public void setRankingState(RankingState rankingState) {
        this.rankingState = rankingState;
    }

    @Override
    public void firePropertyChanged(String eventName) {
        support.firePropertyChange(eventName, null, this.rankingState);
        System.out.println("View Model: ");
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
