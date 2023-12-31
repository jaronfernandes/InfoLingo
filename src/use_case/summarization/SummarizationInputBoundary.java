package use_case.summarization;

/**
 * Implemented by Interactor to obey dependency rule.
 * @author Jaiz Jeeson
 * */
public interface SummarizationInputBoundary {
    /**
     * Checks for problematic summary word counts before sending content for summarisation to SummarisationDataAccessInterface.
     * @param inputData Contains important data for processing, including summary word count and article content.
     */
    void execute(SummarizationInputData inputData);
}
