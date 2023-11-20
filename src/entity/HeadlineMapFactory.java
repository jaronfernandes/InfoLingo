package entity;

public class HeadlineMapFactory implements HeadlineMapFactoryInterface{

    @Override
    public HeadlineMap create(String headline) {
        return new HeadlineMap(headline);
    }
}
