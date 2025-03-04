package factorys;

import interfaces.Image.ImageFlyWeight;
import objects.*;

public class GameObjectFactory {

    public static Tank createTank(Coordinate coordinate, Velocity2D velocity2D) {
        ImageFlyWeight tankImage = ImageFlyWeightFactory.getImage("tank.png");
        return new Tank(coordinate, velocity2D, tankImage);
    }

    public static Bullet createBullet(Coordinate coordinate,Velocity2D velocity2D) {
        ImageFlyWeight bulletImage = ImageFlyWeightFactory.getImage("bullet.png");
        return new Bullet(coordinate,velocity2D,bulletImage);
    }
}
