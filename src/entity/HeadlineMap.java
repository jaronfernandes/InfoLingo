package entity;
import java.util.HashMap;
import java.util.Set;

public class HeadlineMap {

    private HashMap<String, Integer> headlineMap;

    private Integer size;

    public Integer getSize() {
        return size;
    }

    public HashMap<String, Integer> getHeadlineMap() {
        return headlineMap;
    }

    public HeadlineMap(String headline) {
        String[] headlineWords = headline.split("[^a-zA-Z0-9']");
        this.size = headlineWords.length;
        this.headlineMap = new HashMap<>();
        for (String headlineWord : headlineWords) {
            Integer value = headlineMap.putIfAbsent(headlineWord, 1);
            if (value != null) {
                headlineMap.put(headlineWord, value + 1);
            }
        }
    }

    public Float compare(HeadlineMap otherMap){
        Set<String> keys = headlineMap.keySet();
        float sharedTotal = 0;
        for (String key : keys){
            if(otherMap.getHeadlineMap().containsKey(key)){
                sharedTotal += otherMap.getHeadlineMap().get(key) + headlineMap.get(key);
            }
        }
        return sharedTotal / (size + otherMap.getSize());

    }
}
