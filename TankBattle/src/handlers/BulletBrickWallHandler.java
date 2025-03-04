package handlers;

import interfaces.Gameobjects.CollisionHandler;
import objects.CollisionInfo;
import objects.obstacle.BrickWall;
import objects.Bullet;

import static objects.GameConstants.BULLET_DAMAGE;

public class BulletBrickWallHandler implements CollisionHandler<Bullet, BrickWall> {
    @Override
    public void handleCollision(Bullet bullet, BrickWall brickWall) {
        // 获取碰撞信息
        CollisionInfo collision = bullet.getHitBox().intersects(brickWall.getHitBox());
        if (!collision.isColliding()) return;

        brickWall.setHealth(brickWall.getHealth()-BULLET_DAMAGE);
        bullet.setActive(false);
    }
}
