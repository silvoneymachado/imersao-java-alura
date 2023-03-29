import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImdbContentExtractor extends Content implements ContentExtractor<ImdbContentExtractor> {
    private double imDbRating;

    ImdbContentExtractor(int index, String title, double imDbRating, String imageUrl) {
        this.index = index;
        this.title = title;
        this.imDbRating = imDbRating;
        this.imageUrl = imageUrl;
    }

    ImdbContentExtractor() {
    }

    String getInfo() {
        return this.title + " ( " + this.imDbRating + ")";
    }

    public List<ImdbContentExtractor> buildList(String contentBody) {
        List<ImdbContentExtractor> films = new ArrayList<ImdbContentExtractor>();
        var parser = new JSONParser();
        List<Map<String, String>> keyValueList = parser.parse(contentBody);
        int i = 1;
        for (Map<String, String> film : keyValueList) {
            String title = film.get("title");
            double rating = Double.parseDouble(film.get("imDbRating"));
            String imageUrl = film.get("image");

            films.add(new ImdbContentExtractor(i, title, rating, imageUrl));

            i++;
        }
        return films;
    }

    public void createStickers(String requestUrl) {
        List<ImdbContentExtractor> filmList = this.buildList(CustomHttpClient.get(requestUrl));
        var generator = new StickerGenerator();
        int i = 1;
        int listSize = filmList.size();
        System.out.println("processing...");
        for (ImdbContentExtractor film : filmList) {
            System.out.println("creating " + i + " of " + listSize + " films");
            generator.create(film.imageUrl, film.getInfo(), "films/", "film" + "_" + film.index);
            i++;
        }
        System.out.println("films done!");
    }
}
