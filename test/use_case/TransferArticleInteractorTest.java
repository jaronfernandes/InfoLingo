package use_case;

import entity.Article;
import entity.Source;
import org.junit.Test;
import use_case.transfer_article.*;

import static org.junit.Assert.*;
public class TransferArticleInteractorTest {
    @Test
    public void successTest() {
        TransferArticleOutputBoundary transferArticlePresenter = new TransferArticleOutputBoundary() {
            @Override
            public void prepareSuccessView(TransferArticleOutputData outputData) {
                assertNotNull(outputData.getArticle());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case should have passed. Unexpected failure.");
            }
        };
        Article article = new Article("Roblox", "Roblox is great!", new Source("Roblox", "English"), "Jeff", "https://roblox.com", "Singapore", "11-03-2023");
        TransferArticleInputData transferArticleInputData = new TransferArticleInputData(article);

        TransferArticleInputBoundary transferArticleInteractor = new TransferArticleInteractor(transferArticlePresenter);

        transferArticleInteractor.execute(transferArticleInputData);

    }

    @Test
    public void failArticleNotFound() {
        TransferArticleOutputBoundary transferArticlePresenter = new TransferArticleOutputBoundary() {
            @Override
            public void prepareSuccessView(TransferArticleOutputData outputData) {
                fail("Use case should have failed. Unexpected success.");
            }

            @Override
            public void prepareFailView(String error) {
                assertNotEquals(error, "");
            }
        };

        TransferArticleInputData transferArticleInputData = new TransferArticleInputData();

        TransferArticleInputBoundary transferArticleInteractor = new TransferArticleInteractor(transferArticlePresenter);

        transferArticleInteractor.execute(transferArticleInputData);
    }
}