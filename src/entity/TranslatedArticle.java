package entity;

import java.util.ArrayList;
import java.util.List;

public class TranslatedArticle implements Media {
    private String headline;
    private String content;
    private String translatedLanguage;
    private Source source;

    public TranslatedArticle(String headline, String content, Source source, String translatedLanguage) {
        this.headline = headline;
        this.content = content;
        this.translatedLanguage = translatedLanguage;
        this.source = source;
    }

    @Override
    public String getHeadline() {
        return headline;
    }

    public String getContent() {
        return content;
    }

    public String getLanguage() {
        return translatedLanguage;
    }

    public Source getSource() {
        return source;
    }
}
