import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

    public Article[] getSampleNews(String searchTerm) {
        try {
            URI uri = new URI(BASE_URL + "everything/?q=bitcoin&apiKey=" + API_TOKEN);
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
            Article article = new Article(description, author);
            System.out.println(description);
            System.out.println(article.getContent());
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        // Return all articles here.
        return new Article[0];
    }

    private class Article {
        private String content;
        private String author;
        private String title;
        private String url;
        private String source;

        public Article(String content, String author) {
            this.author = author;
            this.content = content;

        }

        public String getContent() {
            return content;
        }
    }
}
