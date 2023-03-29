import java.util.List;
import java.util.Map;

public class Content {
    protected int index;
    protected String title;
    protected String imageUrl;

    public Content(int index, String title, String imageUrl) {
        this.index = index;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public Content() {
    }

    public int getIndex() {
        return index;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    // abstract List<Film> buildList(String contentBody);
}
