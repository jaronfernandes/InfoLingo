package use_case;

import use_case.ArticleRetrievalInputData;
import java.util.List;

public interface ArticleRetrievalInputBoundary {
    void execute(ArticleRetrievalInputData inputData);
}
