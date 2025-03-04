package handlers;

import interfaces.Gameobjects.CollisionHandler;
import objects.*;
import objects.obstacle.Wall;

public class TankWallHandler implements CollisionHandler<Tank, Wall> {

    @Override
    public void handleCollision(Tank tank, Wall wall) {
        CollisionInfo collision = tank.getHitBox().intersects(wall.getHitBox());
        if (collision.isColliding()) {
            Vector2D normal = collision.getNormal();
            double overlap = collision.getOverlap();

            // 调整位置使坦克脱离碰撞
            Coordinate coordinate = tank.getCoordinate();
            coordinate.setX(coordinate.getX() + normal.getX() * overlap);
            coordinate.setY(coordinate.getY() + normal.getY() * overlap);
        }
    }
}
