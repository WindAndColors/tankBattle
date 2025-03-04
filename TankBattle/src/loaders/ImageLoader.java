package loaders;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageLoader {
    private static ImageLoader instance;

    //防止外部实例化
    private ImageLoader() {
    }

    // 获取单例对象
    public static ImageLoader getInstance() {
        if (instance == null) {
            synchronized (ImageLoader.class) {
                if (instance == null) {
                    instance = new ImageLoader();
                }
            }
        }
        return instance;
    }

    // 加载图片
    public BufferedImage loadImage(String imagePath) throws IOException {
        // 拼接imgs文件夹路径
        URL imageUrl = getClass().getClassLoader().getResource("assets/imgs/" + imagePath);
        if (imageUrl == null) {
            throw new IOException("图片未找到" + imagePath);
        }
        return ImageIO.read(imageUrl);
    }
}
