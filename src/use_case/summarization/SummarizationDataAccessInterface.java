package use_case.summarization;

import entity.Article;

import java.util.List;

public interface SummarizationDataAccessInterface {
    /**
     * Method that returns a summary close to length in word count given content to summarise.
     * @param content Content of an article to be summarised.
     * @param length Word count to which summarisation occurs.
     * @return A String representing summarised content.
     * */
    String summarizeArticle(String content, Integer length);
}
