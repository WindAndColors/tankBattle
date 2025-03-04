package gameModel;

import factorys.GameObjectFactory;
import handlers.*;
import interfaces.Gameobjects.Collidable;
import interfaces.Gameobjects.CollisionHandler;
import interfaces.Gameobjects.Drawable;
import interfaces.Gameobjects.Updatable;
import objects.*;
import objects.obstacle.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static objects.GameConstants.BULLET_SPEED;


public class Game implements Drawable, Updatable {
    private Tank player1= GameObjectFactory.createTank(new Coordinate(50,300),new Velocity2D(1,0));
    private Tank player2=GameObjectFactory.createTank(new Coordinate(750,300),new Velocity2D(-1,0));
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    private Map<String,CollisionHandler<?,?>> handlers = new HashMap<>();
    private GameMap gameMap = new GameMap();
    private boolean gameOver;

    public Game(){
        this.gameOver = false;
        handlers.put("坦克与坦克",new TanksHandler());
        handlers.put("子弹与坦克",new BulletTankHandler());
        handlers.put("子弹与砖墙",new BulletBrickWallHandler());
        handlers.put("坦克与墙",new TankWallHandler());
        handlers.put("坦克与河流",new TankRiverHandler());
        handlers.put("子弹与铁墙",new BulletIronWallHandler());
    }

    public Tank getPlayer1() {
        return player1;
    }

    public Tank getPlayer2() {
        return player2;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void fire(Tank tank){
        // 获取坦克当前朝向的旋转角度（弧度）
        RotatedRectangleHitBox tankHitBox = (RotatedRectangleHitBox) tank.getHitBox();
        double rotation = tankHitBox.getRotationRadians();

        // 计算炮管末端位置（坦克正前方）
        double tankCenterX = tank.getCoordinate().getX();
        double tankCenterY = tank.getCoordinate().getY();
        double barrelLength = tank.getWidth() * 0.6; // 炮管长度为坦克宽度的60%

        // 计算子弹生成位置
        double bulletX = tankCenterX + barrelLength * Math.cos(rotation);
        double bulletY = tankCenterY + barrelLength * Math.sin(rotation);

        // 设置子弹初始速度（固定速度+坦克方向）
        Velocity2D bulletVelocity = new Velocity2D(
                BULLET_SPEED * Math.cos(rotation),
                BULLET_SPEED * Math.sin(rotation)
    );

        // 创建子弹并添加到列表
        Bullet bullet = GameObjectFactory.createBullet(new Coordinate(bulletX,bulletY),bulletVelocity);
        bullet.setMoving(true);
        bullet.update(); // 立即初始化 hitBox
        bullets.add(bullet);
    }


    //处理碰撞
    public void handleCollision(Collidable obj1,Collidable obj2){
        String context = null;
        if(obj1 instanceof Tank && obj2 instanceof Tank){
            context = "坦克与坦克";
        }
        if(obj1 instanceof Tank&& obj2 instanceof Wall){
            context = "坦克与墙";
        }
        if(obj1 instanceof Bullet && obj2 instanceof Tank){
            context = "子弹与坦克";
        }
        if(obj1 instanceof Bullet && obj2 instanceof BrickWall){
            context = "子弹与砖墙";
        }
        if(obj1 instanceof Tank && obj2 instanceof River){
            context = "坦克与河流";
        }
        if(obj1 instanceof Bullet && obj2 instanceof IronWall){
            context = "子弹与铁墙";
        }
        if(handlers.containsKey(context)){
            CollisionHandler handler = handlers.get(context);
            handler.handleCollision(obj1,obj2);
        }
    }

    public String getWinner(){
        if(player1.getHealth()>0){
            return "玩家一";
        }else if(player2.getHealth()>0){
            return "玩家二";
        }else{
            return "没有玩家";
        }
    }

    @Override
    public void update(){
        if (gameOver) return;

        // 获取障碍物列表的副本（确保遍历时结构不变）
        List<Obstacle> obstacles = new ArrayList<>(gameMap.getObstacles());

        // 更新玩家和地图
        player1.update();
        player2.update();
        handleCollision(player1,player2);
        gameMap.update(); // 注意：gameMap.update() 可能新增障碍物，但当前帧遍历的是旧副本

        // 处理玩家与障碍物的碰撞（使用副本）
        for (Obstacle obstacle : obstacles) {
            handleCollision(player1, obstacle);
            handleCollision(player2, obstacle);
        }

        // 处理子弹逻辑
        if (!bullets.isEmpty()) {
            Iterator<Bullet> iterator = bullets.iterator();
            while (iterator.hasNext()) {
                Bullet bullet = iterator.next();
                bullet.update();

                // 子弹与玩家碰撞
                handleCollision(bullet, player1);
                handleCollision(bullet, player2);

                // 子弹与障碍物碰撞（使用副本）
                List<Obstacle> bulletObstacles = new ArrayList<>(obstacles);
                for (Obstacle obstacle : bulletObstacles) {
                    handleCollision(bullet, obstacle);
                }

                // 移除失效的子弹
                if (!bullet.isActive()) {
                    iterator.remove();
                }
            }
        }

        // 检查游戏结束
        if (player1.getHealth() <= 0 || player2.getHealth() <= 0) {
            gameOver = true;
        }
    }

    @Override
    public void draw(Graphics2D g2D){
        if(gameOver){
            String winner = getWinner();
            g2D.setColor(Color.RED);
            g2D.setFont(new Font("宋体", Font.BOLD, 50));
            g2D.drawString(winner+"获胜！", 300, 300);  // 显示游戏结束的提示
            return;  // 不再绘制游戏内容
        }

        // 绘制地图
        gameMap.draw(g2D);

        //绘制玩家坦克
        player1.draw(g2D);
        player2.draw(g2D);

        // 绘制所有子弹
        if(!bullets.isEmpty()){
            for (Bullet bullet : bullets) {
                bullet.draw(g2D);
            }
        }
    }
}
