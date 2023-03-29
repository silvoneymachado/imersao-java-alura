import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DotEnv {
    Map<String, String> envKeys;

    void getEnvKeys() {
        File file = new File(".env");
        Scanner myReader;
        try {
            myReader = new Scanner(file);
            Map<String, String> items = new HashMap<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] entry = data.split("=");
                items.put(entry[0], entry[1]);
            }

            myReader.close();
            this.envKeys = items;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    String getKey(String keyName) {
        if (this.envKeys == null) {
            this.getEnvKeys();
        }

        return this.envKeys.get(keyName);
    }
}
