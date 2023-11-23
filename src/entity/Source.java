package entity;

public class Source {
    private String company;
    private String language;

    public Source(String company, String language) {
        this.company = company;
        this.language = language;
    }

    public String getCompany() {
        return company;
    }

    public String getLanguage() {
        return language;
    }
}
