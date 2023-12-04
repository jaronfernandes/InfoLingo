package use_case.summarization;

/**
 * Implemented by DAO to obey dependency rule.
 * @author Jaiz Jeeson
 * */
public interface SummarizationDataAccessInterface {
    /**
     * Method that returns a summary close to length in word count given content to summarise.
     * @param content Content of an article to be summarised.
     * @param length Word count to which summarisation occurs.
     * @return A String representing summarised content.
     * @author Jaiz Jeeson
     * */
    String summarizeArticle(String content, Integer length);
}
