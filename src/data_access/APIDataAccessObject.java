package data_access;

import entity.Article;
import entity.ArticleFactory;
import entity.Source;
import use_case.ArticleRetrievalDataAccessInterface;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;
import java.util.Arrays;
import org.json.*;

import javax.print.URIException;
import java.util.List;

public class APIDataAccessObject implements ArticleRetrievalDataAccessInterface {
    private static final String BASE_URL = "https://newsapi.org/v2/";

    private static final String API_TOKEN = System.getenv("API_TOKEN");


    @Override
    public List<Article> getArticles() {
        return null;
    }

    public Article[] getSampleNews(String rawSearchTerm) {
        Article[] first_article = new Article[1];

        // Replace any spaces with '-' for a valid link.
        String searchTerm = rawSearchTerm.replace(' ', '-');

        Article[] formattedArticles = new Article[0];

        try {
            URI uri = new URI(BASE_URL + "everything/?q=" + searchTerm + "&apiKey=" + API_TOKEN);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().
                    uri(uri).
                    GET().
                    build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            JSONObject data = new JSONObject(body);
//            System.out.println(data);

            // Retrieve first article.
            JSONArray articles = data.getJSONArray("articles");
            formattedArticles = new Article[articles.length()];
            for(int i = 0; i < articles.length(); i++) {
                JSONObject article = articles.getJSONObject(i);
                formattedArticles[i] = formatArticle(article);
//            System.out.println(firstArticle);
            }

            System.out.println("Request for search \"" + rawSearchTerm + "\" successful");
        } catch (Exception e) {
            System.out.println("Request for search \""
                    + rawSearchTerm + "\" unsuccessful - caught " + e);
            System.out.println(Arrays.toString(e.getStackTrace()));
            Article[] nothingArr = {};
            return nothingArr;
        }

        // Return all articles here.
        return formattedArticles;
    }

    private Article formatArticle(JSONObject unformattedArticle){
        // Retrieve details of first article.
        String author = unformattedArticle.getString("author");
        String description = unformattedArticle.getString("description");
        String title = unformattedArticle.getString("title");
        String url = unformattedArticle.getString("url");
        // Not sure how to get the sources from the nested dict
        String source = unformattedArticle.getJSONObject("source").getString("name");
//            System.out.println(source);
        Source sourceObj = new Source(source, "English");

        ArticleFactory articleFactory = new ArticleFactory();
        return articleFactory.create(title, description, sourceObj, author, url);
    }
}
