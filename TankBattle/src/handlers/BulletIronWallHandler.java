package handlers;

import interfaces.Gameobjects.CollisionHandler;
import objects.*;
import objects.obstacle.IronWall;

import java.awt.*;

public class BulletIronWallHandler implements CollisionHandler<Bullet, IronWall>{
    @Override
    public void handleCollision(Bullet bullet, IronWall ironWall) {
        // 获取碰撞信息
        CollisionInfo collision = bullet.getHitBox().intersects(ironWall.getHitBox());
        if (!collision.isColliding()) return;

        Vector2D normal = collision.getNormal();
        double overlap = collision.getOverlap();

        //调整子弹位置使其脱离墙体
        Coordinate coordinate = bullet.getCoordinate();
        coordinate.setX(coordinate.getX() + normal.getX() * (overlap + 0.001));
        coordinate.setY(coordinate.getY() + normal.getY() * (overlap + 0.001));

        //计算反射后的速度
        Velocity2D velocity = bullet.getVelocity();

        //入射速度与单位法线向量 进行 v - 2（v·n）n (反射公式)
        double dot = velocity.getVelocityX() * normal.getX() +
                     velocity.getVelocityY() * normal.getY();
        double reflectX = velocity.getVelocityX() - 2 * dot * normal.getX();
        double reflectY = velocity.getVelocityY() - 2 * dot * normal.getY();

        //更新子弹速度
        velocity.setVelocityX(reflectX);
        velocity.setVelocityY(reflectY);
    }
}
