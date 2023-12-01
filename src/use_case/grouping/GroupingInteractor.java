package use_case.grouping;

import entity.*;

import java.util.ArrayList;
import java.util.HashMap;

public class GroupingInteractor implements GroupingInputBoundary{

    private final GroupingOutputBoundary presenter;


    public GroupingInteractor(GroupingOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(GroupingInputData inputData) {
        GroupingFactory groupingFactory = new GroupingFactory();
        HeadlineMapFactory headlineMapFactory = new HeadlineMapFactory();
        ArrayList<Article> inputArticles = inputData.getArticles();
        ArrayList<Article> articles = new ArrayList<>(inputArticles);
        HashMap<Article, HeadlineMap> articleMaps = new HashMap<>();
        ArrayList<Grouping> groupings = new ArrayList<>();
        for (Article article : articles) {
            articleMaps.put(article, headlineMapFactory.create(article.getHeadline()));
        }
        while (!articles.isEmpty()){
            ArrayList<Article> grouping = new ArrayList<>();
            grouping.add(articles.get(0));
            for (int i = 1; i < articles.size(); i++){
                if(articleMaps.get(articles.get(0)).compare(articleMaps.get(articles.get(i))) > 0.25){
                    grouping.add(articles.get(i));
                    articles.remove(i);
                    i -= 1;
                }
            }
            articles.remove(0);
            groupings.add(groupingFactory.create(grouping));
        }
        if (groupings.isEmpty()) {
            presenter.prepareFailView("Failed to create groups!");
        }
        else {
            GroupingOutputData outputData = new GroupingOutputData(groupings);
            presenter.prepareSuccessView(outputData);
        }

    }
}
