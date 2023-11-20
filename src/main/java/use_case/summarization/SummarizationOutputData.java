package use_case.summarization;

import entity.Article;

import java.util.List;

public class SummarizationOutputData {
    String summarizedText;

    public SummarizationOutputData(String summarizedText) {
        this.summarizedText = summarizedText;
    }

    public String getSummarizedText() {
        return summarizedText;
    }
}
