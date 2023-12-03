package data_access;

//import com.google.cloud.translate.Translate;
//import com.google.cloud.translate.TranslateOptions;
//import com.google.cloud.translate.Translation;

import entity.*;

import use_case.article_retrieval.ArticleRetrievalDataAccessInterface;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import java.util.*;

import org.json.*;

import java.lang.Math;

public class APIDataAccessObject implements ArticleRetrievalDataAccessInterface {
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private final Integer numArticles = 10;
    private final static String API_TOKEN = "724da595748f4aaa9c5692d0aae9fffb";
    private final static String W_API_TOKEN = "cc6707f3f6db448f8800fc5cd0863a78";
    private final boolean useTestAPI = true; // True means DO NOT USE WORLDNEWSAPI. False means you are USING IT.
    private HashMap<String, Article> storedArticles = new HashMap<>();
    private static HashMap<String, HashMap<Article, TranslatedArticle>> storedTranslatedArticles = new HashMap<>();

    @Override
    public List<Article> getArticles(String rawSearchTerm) {
        // Replace any spaces with '-' for a valid link.
        String searchTerm = rawSearchTerm.replace(' ', '-');

        List<Article> formattedArticles = new ArrayList<>();

        try {
            JSONObject data;
            JSONArray articles;

            if (useTestAPI) {
                URI uri = new URI(BASE_URL + "everything/?q=" + searchTerm + "&apiKey=" + API_TOKEN);
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder().
                        uri(uri).
                        GET().
                        build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String body = response.body();
                data = new JSONObject(body);
                articles = data.getJSONArray("articles");
            }
            else {
                URI uri = new URI("https://api.worldnewsapi.com/search-news?text=" + searchTerm + "&api-key=" + W_API_TOKEN);
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder().
                        uri(uri).
                        GET().
                        build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String body = response.body();
                data = new JSONObject(body);
                articles = data.getJSONArray("news");
            }

            // Limits number of articles retrieved to numArticles at most.
            for(int i = 0; i < Math.min(this.numArticles, articles.length()); i++) {
                JSONObject article = articles.getJSONObject(i);
                formattedArticles.add(formatArticle(article));
            }

            removeDuplicateArticles(formattedArticles);

            for (Article article : formattedArticles) {
                storedArticles.put(article.getHeadline(), article);
            }

            System.out.println("Request for search \"" + rawSearchTerm + "\" successful");
        } catch (Exception e) {
            System.out.println("Request for search \""
                    + rawSearchTerm + "\" unsuccessful - caught " + e);
//            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        // Return all articles here.
        return formattedArticles;
    }

    /**
     * <p> Removes duplicate articles from the given List of Article objects through mutation.
     * </p>
     * @param articles the List of Articles.
     * @author Jaron Fernandes
     */
    private void removeDuplicateArticles(List<Article> articles) {
        HashSet<String> existingHeadlines = new HashSet<>();
        int i = 0;

        while (i < articles.size()) {
            Article article = articles.get(i);
            if (!existingHeadlines.contains(article.getHeadline())) {
                existingHeadlines.add(article.getHeadline());
                i++;
            }
            else {
                System.out.println("Removed duplicate Article!");
                articles.remove(article);
            }
        }
    }

    /**
     * <p> Extracts article data from a JSONObject and uses an Article factory to instantiate it as an Article object.
     * </p>
     * @param unformattedArticle a JSONObject containing the article data
     * @author Jaron Fernandes
     */
    private Article formatArticle(JSONObject unformattedArticle){
        // Retrieve details of the article.
        String title = getValue(unformattedArticle, "title");
        String url = getValue(unformattedArticle, "url");
        String author = getValue(unformattedArticle, "author");

        String source, description, country, publishedAt;
        Source sourceObj;
        ArticleFactory articleFactory = new ArticleFactory();

        if (useTestAPI) {
            // Source COMPANY
            source = getValue(unformattedArticle.getJSONObject("source"), "name");
            description = getValue(unformattedArticle, "content").replaceAll("[\\t\\n\\r\\f\\v]", " ");
            country = getValue(unformattedArticle, "country");
            publishedAt = getValue(unformattedArticle, "publishedAt");

            sourceObj = new Source(source, "English");
            return articleFactory.create(title, description, sourceObj, author, url, country, publishedAt);
        }
        else {
            // Source COUNTRY
            source = getValue(unformattedArticle, "source_country");
            description = getValue(unformattedArticle, "text");
            publishedAt = getValue(unformattedArticle, "publish_date");

            sourceObj = new Source(source, getValue(unformattedArticle, "language"));

            return articleFactory.create(title, description, sourceObj, author, url, source, publishedAt);
        }
    }

    /**
     * <p> Retrieves specific article data from a JSONObject by key.
     * </p>
     * @param jsonObject a JSONObject containing the article data
     * @param key A string for the word to extract data from the JSONObject.
     * @author Jaron Fernandes, Jaiz Jeeson
     */
    private String getValue(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getString(key);
        } catch (org.json.JSONException e) {
            System.out.println(e.getMessage());
            return "N.A.";
        }
    }

    /**
     * <p> Returns the associated Article object by headline from the data access object.
     * Throws an exception if there is none.
     * </p>
     * @param headline An article headline as a String.
     * @throws NoSuchElementException if no article with the headline is found
     * @author Jaron Fernandes
     */
    protected Article retrieveArticleByHeadline(String headline) throws NoSuchElementException {
        Article article = storedArticles.get(headline);
        if (article == null) {
            throw new NoSuchElementException();
        }
        return article;
    }

    protected HashMap<String, HashMap<Article, TranslatedArticle>> getStoredTranslatesArticles() {
        return storedTranslatedArticles;
    }
}
