package entity;

public class HeadlineMapFactory implements HeadlineMapFactoryInterface{

    public HeadlineMap create(String headline) {
        return new HeadlineMap(headline);
    }
}
