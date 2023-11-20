package data_access;

import org.json.JSONObject;
import use_case.summarization.SummarizationDataAccessInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SummarisationDataAccessObject implements SummarizationDataAccessInterface {
    private static final String API_TOKEN = "mTgxSjbhFuCmnubxPEiaUbIADVfUoKMnwKdDHUgHCQecAvpXKB";
    private static final String baseUrl= "https://portal.ayfie.com/api/summarize";

    @Override
    public String summarizeArticle(String content, Integer length) {
        String summarisedContent = "";

        String processedContent = String.join("%20", content.split(" "));
//        System.out.println(processedContent);

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
            System.out.println("Couldn't summarise content because of the following error - " + e.getMessage() + ".");
        }

        return summarisedContent;
    }

    public static void main(String[] args) {
        System.out.println(summarizeArticleTest("One of the biggest philosophical traps is this ideal of living minimalistically. Being minimalistic is actually a good thing in itself but many people take that to mean cutting people and things out of your life, not because they are harmful but because it's too much.", 1));
    }

    public static String summarizeArticleTest(String content, Integer length) {
        String summarisedContent = "";

        try {
            URL url = new URL(baseUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            // Specify request method.
            httpURLConnection.setRequestMethod("POST");

            // Specify headers.
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("X-API-KEY", API_TOKEN);

//            System.out.println(httpURLConnection.getResponseCode());
            httpURLConnection.setDoOutput(true);

            String body = "{\"language\": \"auto\", \"text\": \"string\", \"min_length\": 5, \"max_length\": 100}";

            try {
                OutputStream os = httpURLConnection.getOutputStream();
                byte[] input = body.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            } catch (Exception e) {
                System.out.println("Error in writing content - " + e.getMessage());
            }

            // Print headers for debugging.
//            Map<String, List<String>> headers = httpURLConnection.getHeaderFields();
//            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
//                System.out.println(entry.getKey() + ": " + entry.getValue());
//            }

            try {
                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder response = new StringBuilder();

                String currentLine;
                while ((currentLine = bufferedReader.readLine()) != null) {
                    response.append(currentLine.trim());
                }

                httpURLConnection.disconnect();
            } catch (Exception e) {
                System.out.println("Something went wrong with request - " + e.getMessage() + ".");
            }

        } catch (Exception e) {
            System.out.println("Couldn't summarise content because of the following error - " + e.getMessage());
        }

        return summarisedContent;
    }
}