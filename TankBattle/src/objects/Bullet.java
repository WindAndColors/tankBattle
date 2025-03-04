package objects;

import interfaces.Image.ImageFlyWeight;

import java.awt.*;

public class Bullet extends MoveObject {
    private ImageFlyWeight bulletImage;

    public Bullet(Coordinate coordinate,Velocity2D velocity2D,ImageFlyWeight bulletImage) {
        super(coordinate, velocity2D,5, 5,true);
        this.bulletImage = bulletImage;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Graphics2D g2D) {// 绘制子弹
        double x = getCoordinate().getX();
        double y = getCoordinate().getY();
        double width = getWidth();
        double height = getHeight();
        g2D.setColor(Color.BLACK);
        g2D.fill(getHitBox().getShape());
    }
}
