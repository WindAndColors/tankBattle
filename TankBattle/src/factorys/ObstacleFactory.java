package factorys;

import interfaces.Image.ImageFlyWeight;
import objects.obstacle.BrickWall;
import objects.Coordinate;
import objects.obstacle.IronWall;
import objects.obstacle.River;

public class ObstacleFactory {
    public static BrickWall createBrickWall(Coordinate coordinate,double width,double height){
        ImageFlyWeight brickWallImage = ImageFlyWeightFactory.getImage("brickWall.png");
        return new BrickWall(coordinate,width,height,brickWallImage);
    }

    public static IronWall createIronWall(Coordinate coordinate,double width,double height){
        ImageFlyWeight ironWallImage = ImageFlyWeightFactory.getImage("ironWall.png");
        return new IronWall(coordinate,width,height,ironWallImage);
    }

    public static River createRiver(Coordinate coordinate,double width,double height){
        ImageFlyWeight riverImage = ImageFlyWeightFactory.getImage("river.png");
        return new River(coordinate,width,height,riverImage);
    }
}
