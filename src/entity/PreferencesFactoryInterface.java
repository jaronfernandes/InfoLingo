package entity;
import java.util.Date;
import java.util.List;

public interface PreferencesFactoryInterface {

    Preferences create(String language, List<String> keywords, List<String> countries, Date date, Source source);
}
