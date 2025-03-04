package objects;

public class CollisionInfo {
    private boolean isColliding;
    private double overlap; //(投影)重叠量
    private Vector2D normal; //碰撞时碰撞处的单位法线方向向量

    public CollisionInfo(boolean isColliding, double overlap, Vector2D normal) {
        this.isColliding = isColliding;
        this.overlap = overlap;
        this.normal = normal;
    }

    public boolean isColliding() { return isColliding; }
    public double getOverlap() { return overlap; }
    public Vector2D getNormal() { return normal; }
}
