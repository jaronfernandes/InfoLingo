package entity;
import java.util.Date;
import java.util.List;

public class PreferencesFactory implements PreferencesFactoryInterface {

    public Preferences create(String language, List<String> keywords, List<String> countries, Date date, Source source){
        return new Preferences(language, keywords, countries, date, source);
    }
}
