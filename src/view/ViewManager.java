package view;

import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewManager implements PropertyChangeListener {
    private final CardLayout cardLayout;
    private final JPanel views;
    private ViewManagerModel viewManagerModel;
    private HomeView homeView;


    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel, HomeView homeView) {
        this.views = views;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.homeView = homeView;
        this.homeView.addPropertyChangeListener(this);
        this.viewManagerModel.addPropertyChangeListener(this);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Some sort of event.");
        if (evt.getPropertyName().equals("view")) {

            String viewModelName = (String) evt.getNewValue();
            cardLayout.show(views, viewModelName);
        } else if (evt.getPropertyName().equals("switchView")) {
            System.out.println("Recieved event to switch view.");
            if (evt.getNewValue().equals("ArticleView")) {
                cardLayout.show(views, "Article");
            }
        }
    }

    public ViewManagerModel getViewManagerModel() {
        return viewManagerModel;
    }
}
