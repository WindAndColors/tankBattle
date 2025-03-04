package objects.obstacle;

import interfaces.Image.ImageFlyWeight;
import objects.Coordinate;

import java.awt.*;

public class River extends Trap {
    private ImageFlyWeight riverImage;

    public River(Coordinate coordinate,double width,double height,ImageFlyWeight riverImage) {
        super(coordinate, width, height, true);
        this.riverImage = riverImage;
    }

    @Override
    public void draw(Graphics2D g2D) {
        riverImage.drawImage(g2D,
                getCoordinate().getX() - getWidth()/2,
                getCoordinate().getY() - getHeight()/2,
                getWidth(),
                getHeight()
        );
    }

    @Override
    public void update() {

    }
}
