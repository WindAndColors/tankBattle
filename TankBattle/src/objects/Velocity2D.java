package objects;

public class Velocity2D { //直角坐标系
    private double velocityX;//速度的X，Y轴分量
    private double velocityY;

    public Velocity2D() { //默认为0
        this.velocityX = 0;
        this.velocityY = 0;
    }

    public Velocity2D(double velocityX, double velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    //拷贝构造函数
    public Velocity2D(Velocity2D other) {
        this.velocityX = other.velocityX;
        this.velocityY = other.velocityY;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    //获取速度（模长）
    public double getSpeed(){
        return Math.hypot(velocityX,velocityY);
    }

    //获取速度方向（弧度）
    public double getDirectionRadians() {
        return Math.atan2(velocityY, velocityX);
    }

    //获取速度方向（角度）
    public double getDirectionDegrees() {
        return Math.toDegrees(getDirectionRadians());
    }

    //向量加法
    public Velocity2D add(Velocity2D other) {
        return new Velocity2D(this.velocityX + other.velocityX, this.velocityY + other.velocityY);
    }

    //标量乘法
    public Velocity2D multiply(int scalar) {
        return new Velocity2D(this.velocityX * scalar, this.velocityY * scalar);
    }
}
