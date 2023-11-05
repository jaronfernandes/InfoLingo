package data_access;

import entity.Article;
import entity.ArticleFactory;
import entity.Source;
import use_case.article_retrieval.ArticleRetrievalDataAccessInterface;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import org.json.*;

import java.lang.Math;

import java.util.List;

public class APIDataAccessObject implements ArticleRetrievalDataAccessInterface {
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private final Integer numArticles = 10;
    private static String API_TOKEN = "724da595748f4aaa9c5692d0aae9fffb";

    @Override
    public List<Article> getArticles(String rawSearchTerm) {
        // Replace any spaces with '-' for a valid link.
        String searchTerm = rawSearchTerm.replace(' ', '-');


        List<Article> formattedArticles = new ArrayList<>();

        try {
            System.out.println(API_TOKEN);
            URI uri = new URI(BASE_URL + "everything/?q=" + searchTerm + "&apiKey=" + API_TOKEN);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().
                    uri(uri).
                    GET().
                    build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            JSONObject data = new JSONObject(body);
            System.out.println(data);

            // Retrieve first article.
            JSONArray articles = data.getJSONArray("articles");

            // Limits number of articles retrieved to numArticles at most.
            for(int i = 0; i < Math.min(this.numArticles, articles.length()); i++) {
                JSONObject article = articles.getJSONObject(i);
                System.out.println(article);
                formattedArticles.add(formatArticle(article));



//            System.out.println(firstArticle);
            }

            System.out.println("Request for search \"" + rawSearchTerm + "\" successful");
        } catch (Exception e) {
            System.out.println("Request for search \""
                    + rawSearchTerm + "\" unsuccessful - caught " + e);
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        // Return all articles here.
        return formattedArticles;
    }

    private Article formatArticle(JSONObject unformattedArticle){
        // Retrieve details of first article.
        String description = getValue(unformattedArticle, "description");
        String title = getValue(unformattedArticle, "title");
        String url = getValue(unformattedArticle, "url");
        // Not sure how to get the sources from the nested dict
        String source = getValue(unformattedArticle.getJSONObject("source"), "name");
        String author = getValue(unformattedArticle, "author");
//            System.out.println(source);
        Source sourceObj = new Source(source, "English");

        ArticleFactory articleFactory = new ArticleFactory();
        return articleFactory.create(title, description, sourceObj, author, url);
    }

    private String getValue(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getString(key);
        } catch (org.json.JSONException e) {
            System.out.println(e.getMessage());
            return "N.A.";
        }
    }
}
