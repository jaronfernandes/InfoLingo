package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Preferences {
    private String language;
    private List<String> keywords;
    private List<String> countries;
    private String date;
    private Source source;
    private List<Category> categories;

    public Preferences(String language, List<String> keywords, List<String> countries, String date, Source source) {
        this.countries = countries;
        this.date = date;
        this.source = source;
        this.categories = new ArrayList<>();
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public String getLanguage() {
        return language;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public List<String> getCountries() {
        return countries;
    }

    public String getDate() {
        return date;
    }

    public Source getSource() {
        return source;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
