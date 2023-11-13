package data_access;

import org.json.JSONObject;
import use_case.summarization.SummarizationDataAccessInterface;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SummarisationDataAccessObject implements SummarizationDataAccessInterface {
    private static final String API_TOKEN = System.getenv("SUMMARIZATION_API_TOKEN");
    private static final String baseUrl= "https://api.meaningcloud.com/summarization-1.0";

    @Override
    public String summarizeArticle(String content, Integer length) {
        String summarisedContent = "";

        // Every%20action%20reacts.%20What%20was%20precedes%20what%20is%20in%20the%20same%20way%20that%20what%20is%20precedes%20what%20will%20be.
        String processedContent = String.join("%20", content.split(" "));
        System.out.println(processedContent);

        try {
            URI uri = new URI(baseUrl + "&key=" + API_TOKEN + "&txt=" + processedContent + "&sentences=" + length);
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(uri).GET().build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String body = httpResponse.body();
            JSONObject data = new JSONObject(body);
            summarisedContent = data.getString("summary");
        } catch (Exception e) {
            System.out.println("Couldn't summarise content because of the following error - " + e.getMessage() + ".");
        }

        return summarisedContent;
    }
}
