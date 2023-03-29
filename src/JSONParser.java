import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

public class JSONParser {

    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
    private static final Pattern REGEX_JSON_ATTRS = Pattern.compile("\"(.+?)\":\"(.*?)\"");

    public List<Map<String, String>> parse(String json) {
        // solve any format problemas before regex match
        var jsonBody = new JSONObject(json);

        Matcher matcher = REGEX_ITEMS.matcher(jsonBody.toString());
        if (!matcher.find()) {

            throw new IllegalArgumentException("No items have found.");
        }

        String[] items = matcher.group(1).split("\\},\\{");

        List<Map<String, String>> data = new ArrayList<>();

        for (String item : items) {

            Map<String, String> itemAttrs = new HashMap<>();

            Matcher matcherJsonAttrs = REGEX_JSON_ATTRS.matcher(item);
            while (matcherJsonAttrs.find()) {
                String atributo = matcherJsonAttrs.group(1);
                String valor = matcherJsonAttrs.group(2);
                itemAttrs.put(atributo, valor);
            }

            data.add(itemAttrs);
        }

        return data;
    }
}