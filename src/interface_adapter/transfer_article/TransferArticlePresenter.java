package interface_adapter.transfer_article;

import interface_adapter.HomeViewModel;
import interface_adapter.ViewManagerModel;
import use_case.transfer_article.TransferArticleOutputBoundary;
import use_case.transfer_article.TransferArticleOutputData;

/**
 * Presenter class that controls View Model state.
 * Javadocs for methods delegated to interface.
 * @author Jaiz Jeeson
 * @author Jaron Fernandes
 */
public class TransferArticlePresenter implements TransferArticleOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;

    public TransferArticlePresenter(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView(TransferArticleOutputData outputData) {
        viewManagerModel.firePropertyChanged("switchArticleView", outputData);
    }

    @Override
    public void prepareFailView(String error) {
        homeViewModel.firePropertyChanged("transferArticleError", error);
    }
}
