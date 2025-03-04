package objects;

import interfaces.Gameobjects.Destroyable;
import interfaces.Image.ImageFlyWeight;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Tank extends MoveObject implements Destroyable {
    private int health =1; //坦克生命
    private ImageFlyWeight tankImage;

    public Tank(Coordinate coordinate,Velocity2D velocity2D,ImageFlyWeight tankImage) {
        super(coordinate, velocity2D,30, 30, true);
        this.tankImage = tankImage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }


    @Override
    public void update(){
        super.update();
        if(health<=0){
            setActive(false);
        }
    }
    @Override
    public void draw(Graphics2D g2D){
        AffineTransform originalTransform = g2D.getTransform();
        g2D.rotate(getVelocity().getDirectionRadians(),
                getCoordinate().getX(),
                getCoordinate().getY());

        tankImage.drawImage(g2D,
                getCoordinate().getX() - getWidth()/2,
                getCoordinate().getY() - getHeight()/2,
                getWidth(),
                getHeight()
        );

        g2D.setTransform(originalTransform);
    }

    @Override
    public void getHurt(int damage) {
        health -= damage;
    }
}
