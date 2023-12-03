package use_case;

import data_access.APIDataAccessObject;
import entity.Article;
import entity.Source;
import entity.TranslatedArticle;
import org.junit.Test;
import use_case.ranking.*;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RankingInteractorTest {
    @Test
    public void successfulRankingTest() {
        RankingOutputBoundary rankingPresenter = new RankingOutputBoundary() {
            @Override
            public void prepareSuccessView(RankingOutputData outputData) {
                assertEquals("Roblox good", outputData.getRanking().get(0).getHeadline());

            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case should have passed. Unexpected failure.");
            }
        };

        List<String> countries = new ArrayList<>();
        countries.add("ca");
        String date = "1003-02-11";
        List<Article> articles = new ArrayList<>();
        Article distract1 = new Article("Roblox bad", "Roblox is not great!", new Source("EvilCorp", "en"), "Jill", "https://roblox.com", "cn", "2031-11-02");
        Article distract2 = new Article("Roblox good", "Roblox is  great!", new Source("AwesomePeople", "en"), "Jessie", "https://roblox.com", "fr", "1003-02-11");
        Article target = new Article("Roblox alright", "Roblox is  great!", new Source("AwesomePeople", "en"), "Jessie", "https://roblox.com", "ca", "1003-02-11");
        articles.add(distract1);
        articles.add(target);
        articles.add(distract2);
        RankingInputData rankingInputData = new RankingInputData(countries, date, articles);
        RankingInteractor rankingInteractor = new RankingInteractor(rankingPresenter);
        rankingInteractor.execute(rankingInputData);

    }
    @Test
    public void failRankingTest(){
        RankingOutputBoundary rankingPresenter = new RankingOutputBoundary() {
            @Override
            public void prepareSuccessView(RankingOutputData outputData) {
                fail("Use case should have failed. Unexpected success.");
            }

            @Override
            public void prepareFailView(String error) {
                assertNotNull(error);
            }


        };
        List<String> weirdCountries = new ArrayList<>();
        weirdCountries.add("us");
        String date = "1003-02-11";
        List<Article> articles = new ArrayList<>();
        Article distract1 = new Article("Roblox bad", "Roblox is not great!", new Source("EvilCorp", "en"), "Jill", "https://roblox.com", "cn", "2031-11-02");
        Article distract2 = new Article("Roblox good", "Roblox is  great!", new Source("AwesomePeople", "en"), "Jessie", "https://roblox.com", "fr", "1003-02-11");
        Article distract3 = new Article("Roblox alright", "Roblox is  great!", new Source("AwesomePeople", "en"), "Jessie", "https://roblox.com", "ca", "1003-02-11");
        articles.add(distract1);
        articles.add(distract2);
        articles.add(distract3);
        RankingInputData rankingInputData1 =  new RankingInputData(weirdCountries, date, articles);
        RankingInteractor rankingInteractor1 = new RankingInteractor(rankingPresenter);
        rankingInteractor1.execute(rankingInputData1);

    }
    }
}