package factorys;

import interfaces.Image.ImageFlyWeight;
import loaders.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageProxy implements ImageFlyWeight {
    private String imagePath;
    private RealImageFlyWeight realImage;

    public ImageProxy(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public void drawImage(Graphics2D g2D, double x, double y, double width, double height) {
        if (realImage == null) {
            loadRealImage();
        }
        realImage.drawImage(g2D, x, y, width, height);
    }

    private synchronized void loadRealImage() {
        if (realImage == null) {
            try {
                BufferedImage image = ImageLoader.getInstance().loadImage(imagePath);
                realImage = new RealImageFlyWeight(image);
            } catch (IOException e) {
                throw new RuntimeException("Error loading image: " + imagePath, e);
            }
        }
    }
}
