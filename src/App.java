
public class App {
    public static void main(String[] args) throws Exception {
        var dotEnv = new DotEnv();

        String url = dotEnv.getKey("ALURA_IMDB_JSON");
        String urlNasa = dotEnv.getKey("NASA_URL") + "=" + dotEnv.getKey("NASA_API_KEY");

        ContentExtractor<ImdbContentExtractor> film = new ImdbContentExtractor();
        film.createStickers(url);

        ContentExtractor<NasaContentExtractor> nasa = new NasaContentExtractor();
        nasa.createStickers(urlNasa);
    }
}
