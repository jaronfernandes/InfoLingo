package use_case;

import data_access.APIDataAccessObject;
import data_access.TranslationAPIDataAccessObject;
import entity.Article;
import org.junit.Test;
import use_case.translation.*;
import use_case.article_retrieval.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.*;

public class TranslationInteractorTest {

    @Test
    public void successfulTranslateTest() {
        APIDataAccessObject articleRetrievalDataAccessObject = new APIDataAccessObject(true);
        TranslationAPIDataAccessObject translationDataAccessObject = new TranslationAPIDataAccessObject(articleRetrievalDataAccessObject);

        final Article[] articleToTest = new Article[1];
        final HashSet<String> languageMapTest = new HashSet<>();

        // ISO 639-2 Language Codes:
        languageMapTest.add("EN");
        languageMapTest.add("FR");
        languageMapTest.add("ZH");
        languageMapTest.add("JA");
        languageMapTest.add("TR");

        ArticleRetrievalOutputBoundary articleRetrievalPresenter = new ArticleRetrievalOutputBoundary() {
            @Override
            public void prepareSuccessView(ArticleRetrievalOutputData outputData) {
                assertNotEquals(outputData.getArticles().size(), 0);
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
                assertNotEquals(outputData.getTranslatedHeadline(), "");
                assert languageMapTest.contains(outputData.getLanguage());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Translation use case should have passed. Unexpected failure.");
            }
        };

        ArticleRetrievalInputData articleRetrievalInputData = new ArticleRetrievalInputData("roblox");

        ArticleRetrievalInputBoundary articleRetrievalInteractor = new ArticleRetrievalInteractor(articleRetrievalPresenter, articleRetrievalDataAccessObject);

        articleRetrievalInteractor.execute(articleRetrievalInputData);

        TranslationInputData translationInputData = new TranslationInputData(articleToTest[0].getHeadline(), "FR");

        TranslationInputBoundary translationInteractor = new TranslationInteractor(translationPresenter, translationDataAccessObject);

        translationInteractor.execute(translationInputData);
    }

    @Test
    public void failTranslateTest() {
        APIDataAccessObject articleRetrievalDataAccessObject = new APIDataAccessObject(true);
        TranslationAPIDataAccessObject translationDataAccessObject = new TranslationAPIDataAccessObject(articleRetrievalDataAccessObject);
        
        final Article[] articleToTest = new Article[1];
        final String query = "roblox", language = "EA";  // Non-existent ISO 639-2 language!

        ArticleRetrievalOutputBoundary articleRetrievalPresenter = new ArticleRetrievalOutputBoundary() {
            @Override
            public void prepareSuccessView(ArticleRetrievalOutputData outputData) {
                assertNotEquals(outputData.getArticles(), new ArrayList<Article>());
                articleToTest[0] = outputData.getArticles().get(1);
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

        ArticleRetrievalInputData articleRetrievalInputData = new ArticleRetrievalInputData(query);

        ArticleRetrievalInputBoundary articleRetrievalInteractor = new ArticleRetrievalInteractor(articleRetrievalPresenter, articleRetrievalDataAccessObject);

        articleRetrievalInteractor.execute(articleRetrievalInputData);

        TranslationInputData translationInputData = new TranslationInputData(articleToTest[0].getHeadline(), language);

        TranslationInputBoundary translationInteractor = new TranslationInteractor(translationPresenter, translationDataAccessObject);

        translationInteractor.execute(translationInputData);
    }

    @Test
    public void failSearchArticleToTranslateTest() {
        APIDataAccessObject articleRetrievalDataAccessObject = new APIDataAccessObject(true);
        TranslationAPIDataAccessObject translationDataAccessObject = new TranslationAPIDataAccessObject(articleRetrievalDataAccessObject);

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

        TranslationInputData translationInputData = new TranslationInputData("i LOVE pineapple on pizza", "ZH");

        TranslationInputBoundary translationInteractor = new TranslationInteractor(translationPresenter, translationDataAccessObject);

        translationInteractor.execute(translationInputData);
    }
}

