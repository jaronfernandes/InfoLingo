package interface_adapter;

import javax.swing.text.View;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ArticleViewModel extends ViewModel {
    private ArticleState articleState = new ArticleState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public ArticleViewModel(ArticleState articleState) {
        super("article");
        this.articleState = articleState;
    }

    public ArticleState getArticleState() {
        return articleState;
    }

    public void setArticleState(ArticleState articleState) {
        this.articleState = articleState;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.articleState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}