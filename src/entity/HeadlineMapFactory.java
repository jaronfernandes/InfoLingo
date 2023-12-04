package entity;

public class HeadlineMapFactory implements HeadlineMapFactoryInterface{

    /**
     * A Factory that creates a way of tracking the frequency of words in an articles headline,
     * by storing the headline as a map where each word is a key and the value is the number of
     * occurrences of that word in the headline.
     * @param headline
     * @return a new HeadlineMap
     * @author Seth
     */
    @Override
    public HeadlineMap create(String headline) {
        return new HeadlineMap(headline);
    }
}
