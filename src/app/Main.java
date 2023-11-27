package app;

import data_access.APIDataAccessObject;

import interface_adapter.*;
import entity.Article;
import interface_adapter.ArticleViewModel;
import interface_adapter.ArticleState;
import interface_adapter.HomeState;
import interface_adapter.HomeViewModel;
import interface_adapter.ViewManagerModel;
import use_case.article_retrieval.ArticleRetrievalDataAccessInterface;
import use_case.summarization.SummarizationDataAccessInterface;
import use_case.translation.TranslateAPIDataAccessInterface;
import data_access.SummarisationDataAccessObject;
import view.ArticleView;
import view.HomeView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
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

        APIDataAccessObject articleRetrievalDataAccessObject = new APIDataAccessObject();
        SummarizationDataAccessInterface summarisationDataAccessObject = new SummarisationDataAccessObject();

        // Keeps track of views.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeViewModel homeViewModel = new HomeViewModel(new HomeState(new ArrayList<>()));

        GroupingViewModel groupingViewModel = new GroupingViewModel(new GroupingState(new ArrayList<>()));

        // Set initial view.
        HomeView homeView = ArticleRetrievalUseCaseFactory.create(viewManagerModel, homeViewModel, articleRetrievalDataAccessObject, groupingViewModel);

        views.add(homeView, homeView.viewName);

        ArticleViewModel articleViewModel = new ArticleViewModel(new ArticleState());

        ViewManager viewManager = new ViewManager(views, cardLayout, viewManagerModel, homeView);


        // Set initial view.


        ArticleView articleView = TranslationUseCaseFactory.create(
                viewManagerModel,
                articleViewModel,
                articleRetrievalDataAccessObject,
                summarisationDataAccessObject
        );

        views.add(articleView, articleView.viewName);

//        // TODO: FOR ARTICLEVIEW TESTING PURPOSES ONLY, COMMENT THIS PART OUT WHEN DONE!
        viewManagerModel.setActiveView(articleView.viewName);
        viewManagerModel.setActiveView(homeView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
