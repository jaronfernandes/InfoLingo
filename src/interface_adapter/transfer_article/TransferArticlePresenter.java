package interface_adapter.transfer_article;

import entity.Article;
import interface_adapter.HomeState;
import interface_adapter.HomeViewModel;
import interface_adapter.ViewManagerModel;
import use_case.transfer_article.TransferArticleOutputBoundary;
import use_case.transfer_article.TransferArticleOutputData;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TransferArticlePresenter implements TransferArticleOutputBoundary {
    private final ViewManagerModel viewManagerModel;

    public TransferArticlePresenter(ViewManagerModel homeViewModel) {
        this.viewManagerModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView(TransferArticleOutputData outputData) {
        viewManagerModel.firePropertyChanged("switchArticleView", outputData);
    }

    @Override
    public void prepareFailView(String error) {
    }
}
