package objects.obstacle;

import objects.Coordinate;

public abstract class Wall extends Obstacle {

    public Wall(Coordinate coordinate, double width, double height, boolean isActive) {
        super(coordinate, width, height, isActive);
    }
}
