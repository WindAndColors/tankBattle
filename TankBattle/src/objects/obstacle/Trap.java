package objects.obstacle;

import objects.Coordinate;

public abstract class Trap extends Obstacle {
    public Trap(Coordinate coordinate, double width, double height, boolean isActive) {
        super(coordinate, width, height, isActive);
    }
}
