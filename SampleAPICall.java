import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

public class SampleAPICall {
    private static final String BASE_URL = "https://newsapi.org/v2/";

    // Load .env variable corresponding to API token.
    private static final String API_TOKEN = System.getenv("API_TOKEN");

    public static void main(String[] args) {
        System.out.println(getSampleNews("roblox"));
    }

    private static Article[] getSampleNews(String searchTerm) {
        try {
            URI uri = new URI(BASE_URL + "everything/?q=bitcoin&apiKey=" + API_TOKEN);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().
                    uri(uri).
                    GET().
                    build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        return new Article[0];
    }

    private class Article {
        private String content;
        private String author;
        private String title;
        private String url;

        public Article() {
        }
    }
}
