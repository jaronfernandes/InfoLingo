
public class App {
    public static void main(String[] args) {
        SampleAPICall sampleAPICall = new SampleAPICall();
        String searchTerm = "Roblox gaming";

        SampleAPICall.Article[] articles = sampleAPICall.getSampleNews(searchTerm);
        for (int i = 0; i < articles.length; i++) {
            System.out.println("Author: " + articles[0].getAuthor());
            System.out.println("Content: " + articles[0].getContent());
            System.out.println("Title: " + articles[0].getTitle());
            System.out.println("URL: " + articles[0].getUrl());
            System.out.println("Source: " + articles[0].getSource());
        }
    }
}
