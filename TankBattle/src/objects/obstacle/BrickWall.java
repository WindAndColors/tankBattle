package objects.obstacle;

import interfaces.Gameobjects.Destroyable;
import interfaces.Image.ImageFlyWeight;
import objects.Coordinate;

import java.awt.*;

public class BrickWall extends Wall implements Destroyable {
    private int health = 3;
    private ImageFlyWeight brickWallImage;

    public BrickWall(Coordinate coordinate,double width,double height,ImageFlyWeight brickWallImage) {
        super(coordinate, width, height, true);
        this.brickWallImage = brickWallImage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void draw(Graphics2D g2D) {
        brickWallImage.drawImage(g2D,
                getCoordinate().getX() - getWidth()/2,
                getCoordinate().getY() - getHeight()/2,
                getWidth(),
                getHeight()
        );
    }

    @Override
    public void update() {
        if(health<=0){
            setActive(false);
        }
    }

    @Override
    public void getHurt(int damage) {
        health -= damage;
    }
}
