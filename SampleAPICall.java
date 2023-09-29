import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import org.json.*;

import javax.swing.*;

public class SampleAPICall {
    private static final String BASE_URL = "https://newsapi.org/v2/";

    // Load .env variable corresponding to API token.
    private static final String API_TOKEN = System.getenv("API_TOKEN");

    public static void main(String[] args) {

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

            // Retrieve first article.
            JSONArray articles = data.getJSONArray("articles");
            JSONObject firstArticle = articles.getJSONObject(0);

            // Retrieve details of first article.
            String author = firstArticle.getString("author");
            String description = firstArticle.getString("description");
            String title = firstArticle.getString("title");
            String url = firstArticle.getString("url");
            // Not sure how to get the sources from the nested dict
//            String source = firstArticle.getJSONArray("source").getString("id");

            Article article = new Article(description, author, title, url, "temp-source");
//            System.out.println("hi");
//            System.out.println(description);
//            System.out.println(article.getContent());
//            System.out.println("test");
            first_article[0] = article;
        } catch (Exception e) {
            System.out.println("Request for search \""
                    + rawSearchTerm + "\" UNSUCCESSFUL - caught EXCEPTION");
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        System.out.println("Request for search \"" + rawSearchTerm + "\" successful");
        // Return all articles here.
        return first_article;
    }

    protected class Article {
        private String content;
        private String author;
        private String title;
        private String url;
        private String source;

        public Article(String content, String author, String title, String url, String source) {
            this.author = author;
            this.content = content;
            this.title = title;
            this.url = url;
            this.source = source;
        }

        public String getContent() {
            return content;
        }

        public String getAuthor() {
            return author;
        }

        public String getTitle() {
            return title;
        }

        public String getUrl() {
            return url;
        }

        public String getSource() {
            return source;
        }
    }
}
