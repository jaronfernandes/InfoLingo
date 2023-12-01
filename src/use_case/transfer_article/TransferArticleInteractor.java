package use_case.transfer_article;

import entity.*;

import java.util.ArrayList;
import java.util.HashMap;

public class TransferArticleInteractor implements TransferArticleInputBoundary {

    private final TransferArticleOutputBoundary presenter;


    public TransferArticleInteractor(TransferArticleOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(TransferArticleInputData inputData) {
        TransferArticleOutputData outputData = new TransferArticleOutputData(inputData.getArticle());
        presenter.prepareSuccessView(outputData);
    }
}
