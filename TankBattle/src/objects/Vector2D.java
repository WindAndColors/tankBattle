package objects;

public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    //向量点乘
    public double dot(Vector2D other) {
        return this.x * other.x + this.y * other.y;
    }

    //返回垂直向量
    public Vector2D perp() {
        return new Vector2D(-y, x); // 垂直于当前向量的向量
    }

    //返回相反方向向量
    public Vector2D negate() {
        return new Vector2D(-x, -y);
    }
}
