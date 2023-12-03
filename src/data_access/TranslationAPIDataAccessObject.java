package data_access;

import entity.Article;
import entity.TranslatedArticle;
import entity.TranslatedArticleFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.article_retrieval.ArticleRetrievalDataAccessInterface;
import use_case.translation.TranslateAPIDataAccessInterface;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class TranslationAPIDataAccessObject implements TranslateAPIDataAccessInterface {
    private final static String DEEPL_TRANSLATE_API_KEY = "8dbcc2f3-03ef-8e22-3c4a-718f08bbe557:fx";
    private final APIDataAccessObject articleRetrievalDataAccessObject;

    public TranslationAPIDataAccessObject(ArticleRetrievalDataAccessInterface articleRetrievalDataAccessObject) {
        this.articleRetrievalDataAccessObject = (APIDataAccessObject) articleRetrievalDataAccessObject;
    }

    /**
     * <p> Returns a TranslatedArticle object from the given headline. Assumes the article has already been searched
     * for and exists in the data access object. Throws a NoSuchElementException if cannot retrieve article by headline.
     * </p>
     * @param headline An article headline as a String.
     * @param language A String indicating the language in the ISO 639 format.
     * @throws NoSuchElementException if no article with the headline is found.
     * @throws NullPointerException if the API failed to translate the article.
     * @author Jaron Fernandes
     */
    @Override
    public TranslatedArticle translateArticle(String headline, String language) throws NoSuchElementException, NullPointerException {
        Article article = articleRetrievalDataAccessObject.retrieveArticleByHeadline(headline);

        System.out.println(language);
        String translatedHeadline, translatedContent;
        TranslatedArticleFactory translatedArticleFactory = new TranslatedArticleFactory();

        // TEST
        String modifiedHeadline = article.getHeadline().replaceAll("[^A-Za-z0-9_@./#&+\\-\\[\\]\\s]", "");
        String modifiedContent = article.getContent().replaceAll("[^A-Za-z0-9_@./#&+\\-\\[\\]\\s]", "");
        HashMap<String, HashMap<Article, TranslatedArticle>> storedTranslatedArticles = articleRetrievalDataAccessObject.getStoredTranslatesArticles();

        if (storedTranslatedArticles.containsKey(language)) {
            if (storedTranslatedArticles.get(language).containsKey(article)) {
                return storedTranslatedArticles.get(language).get(article);
            }
        }

        try {
            translatedHeadline = translateText(modifiedHeadline, language);
            translatedContent = translateText(modifiedContent, language);
        }
        catch (Exception e) {
            throw new NullPointerException();
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
//        storedArticles.put(finishedTranslatedArticle.getHeadline(), finishedTranslatedArticle);

        return finishedTranslatedArticle;
    }

    private String translateText(String text, String language) throws URISyntaxException, IOException, InterruptedException {
        String formattedText = String.join("%20", text.split(" "));
        // System.out.println(formattedText);

        URI uri = new URI("https://api-free.deepl.com/v2/translate?auth_key=" + DEEPL_TRANSLATE_API_KEY +
                "&text=" + formattedText + "&target_lang=" + language);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRiequest.newBuilder().
                uri(uri).
                GET().
                build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        JSONObject data = new JSONObject(body);

        JSONArray articles = data.getJSONArray("translations");
        JSONObject translation = articles.getJSONObject(0);
//        String source_language = translation.getString("detected_source_language");
        // Note: line above is not needed since it's the same as the language we wanted to translate to!

        return translation.getString("text");
    }
}