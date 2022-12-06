import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 创建地板对象
 */
public class Ground {
    public BufferedImage gd;
    public int x;
    public int y;

    public Ground() {
        try {
            x = 0;
            y = 498;
            gd = ImageIO.read(getClass().getResource("img/ground.png"));
        } catch (IOException e) {
            System.out.println("gd图片加载失败");
        }
    }

    /**
     * 地面的移动方法
     */
    public void step() {
        x--;
        if (x == -216) {
            x = 0;
        }
    }
}
