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

    public Preferences(List<String> countries, String date) {
        this.countries = countries;
        this.date = date;
        this.categories = new ArrayList<>();
    }

    public List<String> getCountries() {
        return countries;
    }

    public String getDate() {
        return date;
    }
}
