package interface_adapter;

import use_case.transfer_article.TransferArticleOutputData;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewManagerModel {

    private String activeViewName;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public String getActiveView() {
        return activeViewName;
    }

    public void setActiveView(String activeView) {
        this.activeViewName = activeView;
    }

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.activeViewName);
    }
    public void firePropertyChanged(String propertyName, TransferArticleOutputData outputData) {
        support.firePropertyChange(propertyName, null, outputData);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
