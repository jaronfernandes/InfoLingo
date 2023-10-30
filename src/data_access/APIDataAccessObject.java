package data_access;

import entity.Article;
import entity.Source;
import use_case.ArticleRetrievalDataAccessInterface;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;
import java.util.Arrays;
import org.json.*;

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
            JSONObject firstArticle = articles.getJSONObject(0);
//            System.out.println(firstArticle);

            // Retrieve details of first article.
            String author = firstArticle.getString("author");
            String description = firstArticle.getString("description");
            String title = firstArticle.getString("title");
            String url = firstArticle.getString("url");
            // Not sure how to get the sources from the nested dict
            String source = firstArticle.getJSONObject("source").getString("name");
//            System.out.println(source);

            Source sourceObj = new Source(source, "English");

            Article article = new Article(title, description, sourceObj);
            first_article[0] = article;
        } catch (Exception e) {
            System.out.println("Request for search \""
                    + rawSearchTerm + "\" unsuccessful - caught " + e);
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        System.out.println("Request for search \"" + rawSearchTerm + "\" successful");
        // Return all articles here.
        return first_article;
    }
}
