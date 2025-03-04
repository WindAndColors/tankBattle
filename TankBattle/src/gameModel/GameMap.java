package gameModel;

import factorys.ObstacleFactory;
import interfaces.Gameobjects.Drawable;
import interfaces.Gameobjects.Updatable;
import objects.*;
import objects.obstacle.BrickWall;
import objects.obstacle.IronWall;
import objects.obstacle.Obstacle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameMap implements Drawable, Updatable {
    private List<Obstacle> obstacles = new ArrayList<>();

    public GameMap(){
        obstacles.add(ObstacleFactory.createIronWall(new Coordinate(40,100),30,30));
        obstacles.add(ObstacleFactory.createIronWall(new Coordinate(70,100),30,30));
        obstacles.add(ObstacleFactory.createIronWall(new Coordinate(100,100),30,30));
        obstacles.add(ObstacleFactory.createIronWall(new Coordinate(130,100),30,30));
        obstacles.add(ObstacleFactory.createIronWall(new Coordinate(160,100),30,30));
        obstacles.add(ObstacleFactory.createBrickWall(new Coordinate(40,200),30,30));
        obstacles.add(ObstacleFactory.createBrickWall(new Coordinate(70,200),30,30));
        obstacles.add(ObstacleFactory.createBrickWall(new Coordinate(100,200),30,30));
        obstacles.add(ObstacleFactory.createBrickWall(new Coordinate(130,200),30,30));
        obstacles.add(ObstacleFactory.createBrickWall(new Coordinate(160,200),30,30));
        obstacles.add(ObstacleFactory.createRiver(new Coordinate(40,300),30,30));
        obstacles.add(ObstacleFactory.createRiver(new Coordinate(70,300),30,30));
        obstacles.add(ObstacleFactory.createRiver(new Coordinate(100,300),30,30));
        obstacles.add(ObstacleFactory.createRiver(new Coordinate(130,300),30,30));
        obstacles.add(ObstacleFactory.createRiver(new Coordinate(160,300),30,30));

    }

    public void addObstacle(Obstacle obstacle){
        obstacles.add(obstacle);
    }

    public List<Obstacle> getObstacles(){
        return obstacles;
    }

    @Override
    public void draw(Graphics2D g2D) {
        obstacles.forEach(obstacle -> obstacle.draw(g2D));
    }

    @Override
    public void update() {
        // 更新所有障碍物状态
        obstacles.forEach(Obstacle::update);
        // 移除已失效的障碍物
        obstacles.removeIf(obstacle -> !obstacle.isActive());
    }
}
