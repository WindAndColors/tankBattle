package objects.obstacle;

import interfaces.Image.ImageFlyWeight;
import objects.Coordinate;
import objects.RotatedRectangleHitBox;

import java.awt.*;

public class IronWall extends Wall {
    private ImageFlyWeight ironWallImage;

    public IronWall(Coordinate coordinate,double width,double height,ImageFlyWeight ironWallImage) {
        super(coordinate, width, height, true);
        this.ironWallImage = ironWallImage;
    }


    @Override
    public void draw(Graphics2D g2D) {
        ironWallImage.drawImage(g2D,
                getCoordinate().getX() - getWidth()/2,
                getCoordinate().getY() - getHeight()/2,
                getWidth(),
                getHeight()
        );
    }

    @Override
    public void update(){

    }
}
