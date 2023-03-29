import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NasaContentExtractor extends Content implements ContentExtractor<NasaContentExtractor> {
    NasaContentExtractor(int index, String title, String imageUrl) {
        this.index = index;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    NasaContentExtractor() {
    }

    String getInfo() {
        return this.title;
    }

    public List<NasaContentExtractor> buildList(String contentBody) {
        String content = contentBody;
        Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
        Matcher matcher = REGEX_ITEMS.matcher(content);
        if (!matcher.find()) {
            content = "{\"items\":[" + content + "],\"errorMessage\":\"\"}";
        }
        List<NasaContentExtractor> planets = new ArrayList<NasaContentExtractor>();
        var parser = new JSONParser();
        List<Map<String, String>> keyValueList = parser.parse(content);
        int i = 1;
        for (Map<String, String> planet : keyValueList) {

            String title = planet.get("title");
            String imageUrl = planet.get("url");

            System.out.println(planet);

            planets.add(new NasaContentExtractor(i, title, imageUrl));

            i++;
        }
        return planets;
    }

    public void createStickers(String requestUrl) {
        List<NasaContentExtractor> planetList = this.buildList(CustomHttpClient.get(requestUrl));

        var generator = new StickerGenerator();
        int i = 1;
        int listSize = planetList.size();
        System.out.println("processing...");
        for (NasaContentExtractor planet : planetList) {
            System.out.println("creating " + i + " of " + listSize + " planets");
            generator.create(planet.imageUrl, planet.getInfo(), "planets/", "planet" + "_" + planet.index);
            i++;
        }
        System.out.println("plants done!");
    }
}
