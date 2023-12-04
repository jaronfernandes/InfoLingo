package data_access;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import use_case.summarization.SummarizationDataAccessInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SummarisationDataAccessObject implements SummarizationDataAccessInterface {
    private static final String API_TOKEN = "QwYkbpsOKMdgMxLnKJFjsKTcFiHCjAXtCMQPcXPwQrubCnjNOc";
    private static final String BASE_URL = "https://portal.ayfie.com/api/summarize";
    private final int variation = 20;

    @Override
    public String summarizeArticle(String content, Integer length) {
        String summarisedContent = "";

        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            // Specify request method.
            httpURLConnection.setRequestMethod("POST");

            // Specify headers.
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("X-API-KEY", API_TOKEN);

            httpURLConnection.setDoOutput(true);

            String body = getBody(content, length, variation);

            // Send request.
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

            // Read output.
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder data = new StringBuilder();

                String currentLine;
                while ((currentLine = bufferedReader.readLine()) != null) {
                    data.append(currentLine.trim());
                }

                // Parse data and retrieve summary.
                JsonObject response = parseResponse(data.toString());
                summarisedContent = response.get("result").getAsString();

                httpURLConnection.disconnect();
            } catch (Exception e) {
                System.out.println("Something went wrong with request - " + e.getMessage() + ".");
            }

        } catch (Exception e) {
            System.out.println("Couldn't summarise content because of the following error - " + e.getMessage());
        }

        return summarisedContent;
    }

    /**
     * Method that returns a formatted body for HTTP request.
     * @param content Content of an article to be summarised.
     * @param length Word count to which summarisation occurs.
     * @param variation Leniency of summary word count - lower the variation, more imprecise the summary word count.
     * @return A String representing body to be sent with HTTP request.
     * */
    private String getBody(String content, int length, int variation) {
        return "{\"language\": \"auto\", \"text\": \"" +
                content +
                "\", \"min_length\": " +
                (length - variation) +
                ", \"max_length\": " +
                (length + variation) +
                "}";
    }

    /**
     * Method that parses data as JSON object.
     * @param data Return value of HTTP request.
     * @return A JSON object that represents the output of the HTTP request.
     * */
    private JsonObject parseResponse(String data) {
        JsonParser jsonParser = new JsonParser();
        JsonObject response = jsonParser.parse(data).getAsJsonObject();
        return response;
    }
}
