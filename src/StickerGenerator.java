import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public class StickerGenerator {
    void create(String imageUrl, String imgText, String outputFileName) {
        // read image
        try {
            InputStream inputStream = new URL(imageUrl).openStream();
            BufferedImage originalImage = ImageIO.read(inputStream);

            // create a new image with transparency and new size
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();
            double percent = 0.08;
            int newHeight = (int) Math.floor(height * percent + height);
            var newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

            // copy the original image to the new in memory imagem
            Graphics2D graphics = (Graphics2D) newImage.getGraphics();
            graphics.drawImage(originalImage, null, 0, 0);

            // font configs
            var font = new Font("Impact", Font.BOLD, width / 20);
            graphics.setFont(font);
            graphics.setColor(Color.yellow);

            // place a phrase after the image
            int imgTextPosition = newHeight - (int) Math.floor(height * (percent / 2));
            graphics.drawString(imgText, 0, imgTextPosition);

            // save new image to the file
            ImageIO.write(newImage, "png", new File("output/" + outputFileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
