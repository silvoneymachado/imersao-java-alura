import java.util.List;

public interface ContentExtractor<T> {
    List<T> buildList(String contentBody);

    public void createStickers(String requestUrl);
}
