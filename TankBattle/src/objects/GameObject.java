package objects;

import interfaces.Gameobjects.Collidable;
import interfaces.Gameobjects.Drawable;
import interfaces.Gameobjects.Updatable;

public abstract class GameObject implements Drawable,Collidable,Updatable{
    private Coordinate coordinate; //游戏对象的中心点坐标
    private double width, height; //游戏对象的大小
    private boolean isActive; //游戏对象是否有效(默认有效)
    private HitBox hitBox; //游戏对象碰撞箱

    public GameObject(Coordinate coordinate, double width, double height,boolean isActive) {
        this.coordinate = coordinate;
        this.width = width;
        this.height = height;
        this.isActive = isActive;
    }

    //默认getter和setter方法,可重写
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

    public HitBox getHitBox() {
        return hitBox;
    }

    public void setHitBox(HitBox hitBox) {
        this.hitBox = hitBox;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
