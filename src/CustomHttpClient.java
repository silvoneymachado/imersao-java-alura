import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class CustomHttpClient {
    public static String get(String url) {
        String body = "";
        try {
            URI uri = URI.create(url);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
            HttpResponse<String> response;
            response = client.send(request, BodyHandlers.ofString());
            body = response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }
}
