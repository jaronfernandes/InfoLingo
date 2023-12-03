package use_case;

import data_access.APIDataAccessObject;
import entity.Article;
import org.junit.Test;
import use_case.translation.*;
import use_case.article_retrieval.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TranslationInteractorTest {

    @Test
    public void successfulTranslateTest() {
        APIDataAccessObject translationDataAccessObject = new APIDataAccessObject();
        final Article[] articleToTest = new Article[1];

        ArticleRetrievalOutputBoundary articleRetrievalPresenter = new ArticleRetrievalOutputBoundary() {
            @Override
            public void prepareSuccessView(ArticleRetrievalOutputData outputData) {
                assertNotEquals(outputData.getArticles(), new ArrayList<Article>());
                articleToTest[0] = outputData.getArticles().get(0);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Article Retrieval use case should have passed for translation test. Unexpected failure.");
            }
        };

        TranslationOutputBoundary translationPresenter = new TranslationOutputBoundary() {
            @Override
            public void prepareSuccessView(TranslationOutputData outputData) {
                assertNotEquals(outputData.getTranslatedContent(), "");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Translation use case should have passed. Unexpected failure.");
            }
        };

        ArticleRetrievalInputData articleRetrievalInputData = new ArticleRetrievalInputData("roblox");

        ArticleRetrievalInputBoundary articleRetrievalInteractor = new ArticleRetrievalInteractor(articleRetrievalPresenter, translationDataAccessObject);

        articleRetrievalInteractor.execute(articleRetrievalInputData);

        TranslationInputData translationInputData = new TranslationInputData(articleToTest[0].getHeadline(), "FR");

        TranslationInputBoundary translationInteractor = new TranslationInteractor(translationPresenter, translationDataAccessObject);

        translationInteractor.execute(translationInputData);
    }

    @Test
    public void failTranslateTest() {
        APIDataAccessObject translationDataAccessObject = new APIDataAccessObject();
        final Article[] articleToTest = new Article[1];

        ArticleRetrievalOutputBoundary articleRetrievalPresenter = new ArticleRetrievalOutputBoundary() {
            @Override
            public void prepareSuccessView(ArticleRetrievalOutputData outputData) {
                assertNotEquals(outputData.getArticles(), new ArrayList<Article>());
                articleToTest[0] = outputData.getArticles().get(0);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Article Retrieval use case should have passed for translation test. Unexpected failure.");
            }
        };

        TranslationOutputBoundary translationPresenter = new TranslationOutputBoundary() {
            @Override
            public void prepareSuccessView(TranslationOutputData outputData) {
                fail("Translation fail case should have failed to translate text and not succeeded.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Failed to translate the article!", error);
            }
        };

        ArticleRetrievalInputData articleRetrievalInputData = new ArticleRetrievalInputData("math proof");

        ArticleRetrievalInputBoundary articleRetrievalInteractor = new ArticleRetrievalInteractor(articleRetrievalPresenter, translationDataAccessObject);

        articleRetrievalInteractor.execute(articleRetrievalInputData);

        TranslationInputData translationInputData = new TranslationInputData(articleToTest[0].getHeadline(), "ZH");

        TranslationInputBoundary translationInteractor = new TranslationInteractor(translationPresenter, translationDataAccessObject);

        translationInteractor.execute(translationInputData);
    }

    @Test
    public void failSearchArticleToTranslateTest() {
        APIDataAccessObject translationDataAccessObject = new APIDataAccessObject();

        TranslationOutputBoundary translationPresenter = new TranslationOutputBoundary() {
            @Override
            public void prepareSuccessView(TranslationOutputData outputData) {
                fail("Translation fail case should have failed to retrieve " +
                        "existing article based on deceptive headline and not succeeded.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Could not find article with the given headline!", error);
            }
        };

        TranslationInputData translationInputData = new TranslationInputData("i LOVE pineapple on pizza", "JA");

        TranslationInputBoundary translationInteractor = new TranslationInteractor(translationPresenter, translationDataAccessObject);

        translationInteractor.execute(translationInputData);
    }
}

