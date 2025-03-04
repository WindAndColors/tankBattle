package objects;

import interfaces.Gameobjects.Movable;

public abstract class MoveObject extends GameObject implements Movable {
    private Velocity2D velocity2D;
    private boolean isMoving =false;

    public MoveObject(Coordinate coordinate,Velocity2D velocity2D, double width, double height,boolean isActive) {
        super(coordinate, width, height,isActive);
        this.velocity2D = velocity2D;
    }

    public Velocity2D getVelocity() {
        return velocity2D;
    }

    public void setVelocity(Velocity2D velocity) {
        this.velocity2D = velocity;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    //默认移动方法，可重写
    @Override
    public void move(){
        if(isMoving){
            Coordinate coordinate = getCoordinate();
            coordinate.setX(coordinate.getX()+velocity2D.getVelocityX());
            coordinate.setY(coordinate.getY()+velocity2D.getVelocityY());
        }
    }

    //默认更新方法,可重写
    @Override
    public void update(){
        move();
        setHitBox(new RotatedRectangleHitBox(
                getCoordinate(),
                getWidth(),
                getHeight(),
                getVelocity().getDirectionRadians()
        ));
    }
}
