package use_case;

import data_access.APIDataAccessObject;
import entity.Article;
import org.junit.Test;
import use_case.article_retrieval.*;
import use_case.translation.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ArticleRetrievalInteractorTest {

    @Test
    public void successfulArticleRetrieval() {
        APIDataAccessObject translationDataAccessObject = new APIDataAccessObject(true);

        ArticleRetrievalOutputBoundary articleRetrievalPresenter = new ArticleRetrievalOutputBoundary() {
            @Override
            public void prepareSuccessView(ArticleRetrievalOutputData outputData) {
                assertNotEquals(outputData.getArticles().size(), 0);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Article Retrieval use case should have passed for simple query \"roblox\". Unexpected failure.");
            }
        };

        ArticleRetrievalInputData articleRetrievalInputData = new ArticleRetrievalInputData("roblox");

        ArticleRetrievalInputBoundary articleRetrievalInteractor = new ArticleRetrievalInteractor(articleRetrievalPresenter, translationDataAccessObject);

        articleRetrievalInteractor.execute(articleRetrievalInputData);
    }

    @Test
    public void failRetrieveArticleTest() {
        APIDataAccessObject translationDataAccessObject = new APIDataAccessObject(true);

        ArticleRetrievalOutputBoundary articleRetrievalPresenter = new ArticleRetrievalOutputBoundary() {
            @Override
            public void prepareSuccessView(ArticleRetrievalOutputData outputData) {
                fail("Should not have found an article with the given complex headline. Unexpected failure.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "Failed to retrieve any articles!");
            }
        };

        ArticleRetrievalInputData articleRetrievalInputData = new ArticleRetrievalInputData("The Sierpinski Gasket makes me wanna cry");

        ArticleRetrievalInputBoundary articleRetrievalInteractor = new ArticleRetrievalInteractor(articleRetrievalPresenter, translationDataAccessObject);

        articleRetrievalInteractor.execute(articleRetrievalInputData);
    }
}

