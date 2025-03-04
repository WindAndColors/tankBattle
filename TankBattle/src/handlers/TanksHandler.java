package handlers;

import interfaces.Gameobjects.CollisionHandler;
import objects.CollisionInfo;
import objects.Tank;

public class TanksHandler implements CollisionHandler<Tank, Tank> {
    @Override
    public void handleCollision(Tank tank1, Tank tank2) {
        // 获取碰撞信息
        CollisionInfo collision = tank1.getHitBox().intersects(tank2.getHitBox());
        if (!collision.isColliding()) return;

        tank1.setHealth(-1);
        tank2.setHealth(-1);
    }
}
