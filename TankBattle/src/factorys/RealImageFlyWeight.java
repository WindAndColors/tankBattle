package factorys;

import interfaces.Image.ImageFlyWeight;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RealImageFlyWeight implements ImageFlyWeight {
    private BufferedImage image;

    public RealImageFlyWeight(BufferedImage image) {
        this.image = image;
    }

    @Override
    public void drawImage(Graphics2D g2D, double x, double y, double width, double height) {
        g2D.drawImage(image,(int)x,(int)y,(int)width,(int)height,null);
    }
}
