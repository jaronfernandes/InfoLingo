package data_access;

import entity.Article;
import entity.TranslatedArticle;
import org.json.JSONObject;
import use_case.translation.TranslateAPIDataAccessInterface;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TranslateAPIDataAccessObject implements TranslateAPIDataAccessInterface {
    private static final String BASE_URL = "https://libretranslate.com/translate/";

    private static final String API_TOKEN = System.getenv("API_TOKEN");
    /*
    const res = await fetch("https://libretranslate.com/translate", {
        method: "POST",
        body: JSON.stringify({
            q: "le poisson le poisson comment j'aime le poisson",
            source: "auto",
            target: "en",
            format: "text",
            api_key: ""
        }),
        headers: { "Content-Type": "application/json" }
    });

    console.log(await res.json());
    */

    @Override
    public TranslatedArticle translateArticle(Article article) {
        return new TranslatedArticle(article.getHeadline(), article.getContent(), article.getSource(), "fr", "me", "url");
    }
}
