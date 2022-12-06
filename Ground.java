import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 创建地板对象
 */
public class Ground {
    //创建图片暂存区
    public BufferedImage gd;
    //设置地板的位置
    public int x;
    public int y;

    public Ground() {
        try {
            //初始化地板的位置
            x = 0;
            y = 498;
            //将图片赋给图片暂存区对象
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
