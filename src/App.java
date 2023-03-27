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

        // extrair os dados (titulo, poster, classificação)
        var parser = new JSONParser();
        List<Map<String, String>> filmList = parser.parse(body);

        int j = 1;

        for (Map<String, String> film : filmList) {
            System.out.println("#" + j);
            System.out.println("Title: " + film.get("title"));
            System.out.println("poster: " + film.get("image"));
            var rating = film.get("imDbRating");
            System.out.printf("IMDB Rating: ");
            if (rating != null) {
                var ratingNumber = Double.parseDouble(rating);
                for (int i = 0; i < Math.floor(ratingNumber); i++) {
                    System.out.printf("* ");
                }
            }
            System.out.printf("(" + rating + ")");
            System.out.println();
            System.out.println();

            j++;
        }

        // exibir e manipular os dados
    }
}
