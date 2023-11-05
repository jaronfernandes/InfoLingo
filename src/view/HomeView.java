package view;

import interface_adapter.article_retrieval.ArticleRetrievalController;
import interface_adapter.HomeState;
import interface_adapter.HomeViewModel;
import interface_adapter.article_retrieval.ArticleRetrievalPresenter;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomeView extends JPanel implements PropertyChangeListener{
    public final String viewName = "Home";
    HomeViewModel homeViewModel;
    ArticleRetrievalController articleRetrievalController;
    ArticleRetrievalPresenter articleRetrievalPresenter;




    //Where the GUI is created:


    public HomeView(ArticleRetrievalController controller, HomeViewModel homeViewModel) {
        this.articleRetrievalController = controller;
        this.homeViewModel = homeViewModel;
        homeViewModel.addPropertyChangeListener(this);

        //Page

        //Headlines



        for (String headline: homeViewModel.getHomeState().getHeadlines()) {
            JLabel Headline = new JLabel(headline);
            this.add(Headline);
        }
        //this.add(news);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        HomeState state = (HomeState) evt.getNewValue();
    }
}