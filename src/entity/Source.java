package entity;

public class Source {
    private String entity;
    private String language;

    /**
     * Source entity - source company/country and the source langauge of an Article.
     * @param entity The company of country where the article originated.
     * @param language The language the article is written in.
     * @author Jaron Fernandes
     */
    public Source(String entity, String language) {
        this.entity = entity;
        this.language = language;
    }

    public String getEntity() {
        return entity;
    }

    public String getLanguage() {
        return language;
    }
}
