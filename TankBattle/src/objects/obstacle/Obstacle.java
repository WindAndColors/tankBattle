package objects.obstacle;

import objects.Coordinate;
import objects.GameObject;
import objects.RotatedRectangleHitBox;

public abstract class Obstacle extends GameObject {
    public Obstacle(Coordinate coordinate, double width, double height, boolean isActive) {
        super(coordinate, width, height, isActive);
        this.setHitBox(new RotatedRectangleHitBox(getCoordinate(),getWidth(),getHeight(),0));
    }
}
