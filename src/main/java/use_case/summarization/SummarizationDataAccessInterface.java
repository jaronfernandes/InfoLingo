package use_case.summarization;

import entity.Article;

import java.util.List;

public interface SummarizationDataAccessInterface {
    String summarizeArticle(String content, Integer length);
}
