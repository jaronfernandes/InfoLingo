package app;

import data_access.APIDataAccessObject;
import data_access.SummarisationDataAccessObject;
import interface_adapter.*;
import use_case.summarization.SummarizationDataAccessInterface;
import view.ArticleView;
import view.HomeView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ArticleViewMain {
    public static void main(String[] args) {
        SummarisationDataAccessObject summarisationDataAccessObjectFake = new SummarisationDataAccessObject();
        summarisationDataAccessObjectFake.summarizeArticle("One of the biggest philosophical traps is this ideal of living minimalistically. Being minimalistic is actually a good thing in itself but many people take that to mean cutting people and things out of your life, not because they are harmful but because it's too much. A lot of people that subscribe to minimalism fall into the same but opposite category of obsessives as materialistic people. Materialistic people gather resources for the sake of it and to feel they are moving up in the world. They get dopamine from acquiring the latest new thing. Minimalists throw out everything they have regardless of its sentimental value and they get dopamine from it because they believe they are moving forward in the world by having absolutely nothing to their name except the essentials. Having things with sentimental value is incredibly important for growth and happiness, regardless of what it is.", 2);
        // Application window.
        JFrame application = new JFrame("InfoLingo");

        // menu end
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // Instantiate views.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // Keeps track of views.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        ArticleViewModel articleViewModel = getArticleViewModel();
        SummarizationDataAccessInterface summarisationDataAccessObject = new SummarisationDataAccessObject();

        // Set initial view.
        ArticleView articleView = SummarisationUseCaseFactory.create(viewManagerModel, articleViewModel, summarisationDataAccessObject);
        views.add(articleView, articleView.getName());

        viewManagerModel.setActiveView(articleView.getName());
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }

    private static ArticleViewModel getArticleViewModel() {
        ArticleState articleState = new ArticleState();
        articleState.setOriginalContent("One of the biggest philosophical traps is this ideal of living minimalistically. Being minimalistic is actually a good thing in itself but many people take that to mean cutting people and things out of your life, not because they are harmful but because it's too much. A lot of people that subscribe to minimalism fall into the same but opposite category of obsessives as materialistic people. Materialistic people gather resources for the sake of it and to feel they are moving up in the world. They get dopamine from acquiring the latest new thing. Minimalists throw out everything they have regardless of its sentimental value and they get dopamine from it because they believe they are moving forward in the world by having absolutely nothing to their name except the essentials. Having things with sentimental value is incredibly important for growth and happiness, regardless of what it is.");
        return new ArticleViewModel(articleState);
    }
}
