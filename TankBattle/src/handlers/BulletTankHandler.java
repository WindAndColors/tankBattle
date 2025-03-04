package handlers;

import interfaces.Gameobjects.CollisionHandler;
import objects.Bullet;
import objects.CollisionInfo;
import objects.Tank;

import static objects.GameConstants.BULLET_DAMAGE;

public class BulletTankHandler implements CollisionHandler<Bullet, Tank> {
    @Override
    public void handleCollision(Bullet bullet, Tank tank) {
        // 获取碰撞信息
        CollisionInfo collision = bullet.getHitBox().intersects(tank.getHitBox());
        if (!collision.isColliding()) return;

        tank.setHealth(tank.getHealth()-BULLET_DAMAGE);
        bullet.setActive(false);
    }
}
