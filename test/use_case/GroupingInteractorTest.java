package use_case;
import entity.Article;
import entity.Source;
import org.junit.Test;

import use_case.grouping.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GroupingInteractorTest {

    @Test
    public void failNoArticlesTest() {
        GroupingOutputBoundary groupingPresenter = new GroupingOutputBoundary() {
            @Override
            public void prepareSuccessView(GroupingOutputData outputData) {
                fail("Use case should have failed. Unexpected success.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "Failed to create groups!");
            }
        };

        GroupingInputData groupingInputData = new GroupingInputData(new ArrayList<>());

        GroupingInputBoundary groupingInteractor = new GroupingInteractor(groupingPresenter);

        groupingInteractor.execute(groupingInputData);
    }

    @Test
    public void successNoGroupTest(){
        GroupingOutputBoundary groupingPresenter = new GroupingOutputBoundary() {
            @Override
            public void prepareSuccessView(GroupingOutputData outputData) {
                assertEquals(outputData.getGroupings().size(), 2);
            }

            @Override
            public void prepareFailView(String error) {
                //fail("Use case failed.");
            }
        };

        ArrayList<Article> articles = new ArrayList<Article>();
        articles.add(new Article("headline 1", "", new Source("", ""), "", "", "", "01-22-2020"));
        articles.add(new Article("completely different words", "", new Source("", ""), "", "", "", "02-22-2020"));
        GroupingInputData groupingInputData = new GroupingInputData(articles);

        GroupingInputBoundary groupingInteractor = new GroupingInteractor(groupingPresenter);

        groupingInteractor.execute(groupingInputData);
    }

    @Test
    public void successOneGroupTest(){
        GroupingOutputBoundary groupingPresenter = new GroupingOutputBoundary() {
            @Override
            public void prepareSuccessView(GroupingOutputData outputData) {
                assertEquals(outputData.getGroupings().size(), 1);
            }

            @Override
            public void prepareFailView(String error) {
                //fail("Use case failed.");
            }
        };

        ArrayList<Article> articles = new ArrayList<Article>();
        articles.add(new Article("same string", "", new Source("", ""), "", "", "", "01-22-2020"));
        articles.add(new Article("same string", "", new Source("", ""), "", "", "", "02-22-2020"));
        GroupingInputData groupingInputData = new GroupingInputData(articles);

        GroupingInputBoundary groupingInteractor = new GroupingInteractor(groupingPresenter);

        groupingInteractor.execute(groupingInputData);
    }
}
