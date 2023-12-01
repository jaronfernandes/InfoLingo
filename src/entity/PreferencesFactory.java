package entity;
import java.util.Date;
import java.util.List;

public class PreferencesFactory implements PreferencesFactoryInterface {

    public Preferences create(List<String> countries, String date){
        return new Preferences(countries, date);
    }
}
