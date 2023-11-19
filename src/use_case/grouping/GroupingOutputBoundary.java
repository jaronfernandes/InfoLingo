package use_case.grouping;

import use_case.article_retrieval.ArticleRetrievalOutputData;

public interface GroupingOutputBoundary {

    void prepareSuccessView(GroupingOutputData outputData);
    void prepareFailView(String error);
}
