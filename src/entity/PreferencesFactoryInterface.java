package entity;
import java.util.Date;
import java.util.List;

public interface PreferencesFactoryInterface {

    Preferences create(List<String> countries, String date);
}
