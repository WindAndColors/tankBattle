package interfaces.Gameobjects;

public interface CollisionHandler<A extends Collidable, B extends Collidable> {
    void handleCollision(A obj1, B obj2); // 处理A和B的碰撞
}