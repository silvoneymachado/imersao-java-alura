import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer get do IMDB e buscar os top 250 filmes

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI uri = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        var generator = new StickerGenerator();

        // extrair os dados (titulo, poster, classificação)
        var parser = new JSONParser();
        List<Map<String, String>> filmList = parser.parse(body);

        int j = 1;

        for (Map<String, String> film : filmList) {
            String filmInfo = "";

            // filmInfo += "#" + j + "\n\r";
            filmInfo += film.get("title") + " \n\r";
            // filmInfo += film.get("image") + "\n\r";
            var rating = film.get("imDbRating");

            filmInfo += "(" + rating + ")" + "\n\r";

            generator.create(film.get("image"), filmInfo, "film" + "_" + j);

            j++;
        }
    }
}
