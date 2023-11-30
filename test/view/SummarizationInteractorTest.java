package view;

import data_access.SummarisationDataAccessObject;
import org.junit.Test;
import use_case.summarization.*;

import static org.junit.Assert.*;

public class SummarizationInteractorTest {

    @Test
    public void successTest() {
        SummarizationDataAccessInterface summarizationDataAccessObject = new SummarisationDataAccessObject();

        SummarizationOutputBoundary summarizationPresenter = new SummarizationOutputBoundary() {
            @Override
            public void prepareSuccessView(SummarizationOutputData outputData) {
                assertNotEquals(outputData.getSummarizedText(), "");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case should have passed. Unexpected failure.");
            }
        };

        SummarizationInputData summarizationInputData = new SummarizationInputData("This is valid content for an article.", 40);

        SummarizationInputBoundary summarizationInteractor = new SummarizationInteractor(summarizationPresenter, summarizationDataAccessObject);

        summarizationInteractor.execute(summarizationInputData);
    }

}