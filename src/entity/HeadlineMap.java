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

    /**
     * A way of tracking the frequency of words in an articles headline, by storing the headline
     * as a map where each word is a key and the value is the number of occurrences of that word in
     * the headline.
     * @param headline
     * @author Seth
     */
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

    /**
     * Compares the similarities of two HeadlineMaps with respect to the amount of words they have in common
     * @param otherMap another HeadlineMap to be compared with the original
     * @return a float between 0.0 and 1.0 denoting the similarity of the two HeadlineMaps
     * @author Seth
     */
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
