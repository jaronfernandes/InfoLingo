package data_access;

import org.json.JSONObject;
import use_case.summarization.SummarizationDataAccessInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;


public class SummarisationAPIDataAccessObject implements SummarizationDataAccessInterface {
    private static final String API_TOKEN = "984026c489d9153657df3e47f70cfe0e";
    private static final String baseUrl = "https://api.meaningcloud.com/summarization-1.0";

    @Override
    public String summarizeArticle(String content, Integer length) {
        return null;
    }

    public static void main(String[] args) {
        System.out.println(summarizeArticleTest("One of the biggest philosophical traps is this ideal of living minimalistically. Being minimalistic is actually a good thing in itself but many people take that to mean cutting people and things out of your life, not because they are harmful but because it's too much.", 1));
    }

    public static String summarizeArticleTest(String content, Integer length) {
        String summarisedContent = "";
        String processedContent = String.join("%20", content.split(" "));

        try {
            URI uri = new URI(baseUrl + "&key=" + API_TOKEN + "&txt=" + processedContent + "&sentences=" + length + "&of=json");
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(uri).GET().build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String body = httpResponse.body();
            JSONObject data = new JSONObject(body);
            summarisedContent = data.getString("summary");
        } catch (Exception e) {
            System.out.println("Couldn't summarise content because of the following error - " + e.getMessage());
        }

        return summarisedContent;
    }
}
