package objects;

import java.awt.*;

public abstract class HitBox{
    private Coordinate coordinate; //碰撞箱中心点坐标
    private double width; //碰撞箱大小
    private double height;

    public HitBox(Coordinate coordinate, double width, double height) {
        this.coordinate = coordinate;
        this.width = width;
        this.height = height;
    }

    public HitBox() {
    }

    //默认getter和setter方法
    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public abstract CollisionInfo intersects(HitBox other);

    public abstract Shape getShape();
}
