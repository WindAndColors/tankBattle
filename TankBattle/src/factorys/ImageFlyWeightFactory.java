package factorys;

import interfaces.Image.ImageFlyWeight;

import java.util.HashMap;
import java.util.Map;

public class ImageFlyWeightFactory {
    private static Map<String, ImageFlyWeight>  images = new HashMap<>();

    public static ImageFlyWeight getImage(String imagePath) {
        return images.computeIfAbsent(imagePath, k -> new ImageProxy(imagePath));
    }

    // 预加载常用资源
    public static void preloadCommonImages() {
        getImage("BrickWall.png");
        getImage("IronWall.png");
        getImage("River.png");
        getImage("tank.png");
    }
}
