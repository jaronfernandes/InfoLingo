package data_access;

// TODO: Need to add Gradle or Maven dependency for this to work!
//import com.google.cloud.translate.Translate;
//import com.google.cloud.translate.TranslateOptions;
//import com.google.cloud.translate.Translation;

import entity.Article;
import entity.ArticleFactory;
import entity.TranslatedArticleFactory;
import entity.Source;

import entity.TranslatedArticle;
import use_case.article_retrieval.ArticleRetrievalDataAccessInterface;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import java.util.*;

import org.json.*;
import use_case.summarization.SummarizationDataAccessInterface;
import use_case.translation.TranslateAPIDataAccessInterface;

import javax.print.URIException;
import java.lang.Math;

public class APIDataAccessObject implements ArticleRetrievalDataAccessInterface, TranslateAPIDataAccessInterface {
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private final Integer numArticles = 10;
    private static String API_TOKEN = "724da595748f4aaa9c5692d0aae9fffb";
    private static String DEEPL_TRANSLATE_API_KEY = "8dbcc2f3-03ef-8e22-3c4a-718f08bbe557:fx";
    private static HashMap<String, HashMap<Article, TranslatedArticle>> storedTranslatedArticles = new HashMap<>();

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
//                System.out.println(article);
                formattedArticles.add(formatArticle(article));
//            System.out.println(firstArticle);
            }
            removeDuplicateArticles(formattedArticles);

            System.out.println("Request for search \"" + rawSearchTerm + "\" successful");
        } catch (Exception e) {
            System.out.println("Request for search \""
                    + rawSearchTerm + "\" unsuccessful - caught " + e);
            System.out.println(Arrays.toString(e.getStackTrace()));
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

    private Article formatArticle(JSONObject unformattedArticle){
        // Retrieve details of first article.
        String description = getValue(unformattedArticle, "content").replaceAll("[\\t\\n\\r\\f\\v]", " ");
        System.out.println(description);
        System.out.println("IT HAPPENED)");

        String title = getValue(unformattedArticle, "title");
        String url = getValue(unformattedArticle, "url");
        // Not sure how to get the sources from the nested dict
        String source = getValue(unformattedArticle.getJSONObject("source"), "name");
        String author = getValue(unformattedArticle, "author");
        String country = getValue(unformattedArticle, "country");
        String publishedAt = getValue(unformattedArticle, "publishedAt");
//            System.out.println(source);
        Source sourceObj = new Source(source, "English");

        ArticleFactory articleFactory = new ArticleFactory();
        return articleFactory.create(title, description, sourceObj, author, url, country, publishedAt);
    }

    private String getValue(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getString(key);
        } catch (org.json.JSONException e) {
            System.out.println(e.getMessage());
            return "N.A.";
        }
    }

    @Override
    public TranslatedArticle translateArticle(Article article, String language) {
        System.out.println(language);
        String translatedHeadline = null, translatedContent = null;
        TranslatedArticleFactory translatedArticleFactory = new TranslatedArticleFactory();

        // TEST
        String modifiedContent = article.getContent().replaceAll("[\\t\\n\\r\\f\\v]", " ");
//        translatedContent = article.getContent();
        System.out.println(translatedContent);

        // sample: https://api-free.deepl.com/v2/translate?auth_key=
        // 8dbcc2f3-03ef-8e22-3c4a-718f08bbe557:fx
        // &text=This%20is%20a%20Test.&target_lang=JA
        if (storedTranslatedArticles.containsKey(language)) {
            if (storedTranslatedArticles.get(language).containsKey(article)) {
                return storedTranslatedArticles.get(language).get(article);
            }
        }

        try {
            translatedHeadline = translateText(article.getHeadline(), language);
            System.out.println(translatedHeadline);
            translatedContent = translateText(modifiedContent, language);
            System.out.println(translatedContent);
        }
        catch (Exception e) {
            // try using Google's instead as a backup! (due to char limit on DeepL)
            // TODO: API call for translating content (need Gradle/Maven dependency thing)
            try {

            } catch (Exception e2) {

            }
        }
        if (translatedContent == null || translatedHeadline == null) {
            // Throw a null pointer exception if unable to retrieve any translated text.
            throw new NullPointerException();
        }
        TranslatedArticle finishedTranslatedArticle = translatedArticleFactory.create(
                translatedHeadline,
                translatedContent,
                article.getSource(),
                language,
                article.getAuthor(),
                article.getURL(),
                article.getCountry(),
                article.getPublishedAt()
        );

        if (storedTranslatedArticles.containsKey(language)) {
            storedTranslatedArticles.get(language).put(article, finishedTranslatedArticle);
        }
        else {
            HashMap<Article, TranslatedArticle> newTranslatedArticles = new HashMap<>();
            newTranslatedArticles.put(article, finishedTranslatedArticle);
            storedTranslatedArticles.put(language, newTranslatedArticles);
        }

        return finishedTranslatedArticle;
    }

    private String translateText(String text, String language) throws URISyntaxException, IOException, InterruptedException {
        String formattedText = String.join("%20", text.split(" "));
        // System.out.println(formattedText);

        URI uri = new URI("https://api-free.deepl.com/v2/translate?auth_key=" + DEEPL_TRANSLATE_API_KEY +
                "&text=" + formattedText + "&target_lang=" + language);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().
                uri(uri).
                GET().
                build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        JSONObject data = new JSONObject(body);

//        System.out.println(data);
//        System.out.println(language);

        JSONArray articles = data.getJSONArray("translations");
        JSONObject translation = articles.getJSONObject(0);
//        String source_language = translation.getString("detected_source_language");
        // Note: line above is not needed since it's the same as the language we wanted to translate to!

        return translation.getString("text");
    }
}
