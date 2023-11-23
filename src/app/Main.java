package app;

import data_access.APIDataAccessObject;
import data_access.SummarisationDataAccessObject;
import interface_adapter.*;
import use_case.article_retrieval.ArticleRetrievalDataAccessInterface;
import view.ArticleView;
import view.HomeView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
//<<<<<<< Updated upstream
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
//=======
//>>>>>>> Stashed changes
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Application window.
        JFrame application = new JFrame("InfoLingo");

        // menu end
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // Instantiate views.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // Keeps track of views.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        HomeViewModel homeViewModel = new HomeViewModel(new HomeState(new ArrayList<>()));
        ArticleRetrievalDataAccessInterface articleRetrievalDataAccessObject = new APIDataAccessObject();

        // Set initial view.
        HomeView homeView = ArticleRetrievalUseCaseFactory.create(viewManagerModel, homeViewModel, articleRetrievalDataAccessObject);
        views.add(homeView, homeView.viewName);

        viewManagerModel.setActiveView(homeView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
