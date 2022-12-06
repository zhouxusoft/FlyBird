import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Bird {
    public BufferedImage image;
    public int x;
    public int y;
    public int i = 0, k = 0;

    public Bird() {
        try {
            x = 120;
            y = 220;
            image = ImageIO.read(getClass().getResource("img/0.png"));
        } catch (IOException e) {
            System.out.println("Bird图片加载失败");
        }
    }

    public void fly() {
        
        /**
         * 一个图片显示6次，这样小鸟飞的就会更慢
         */
        k++;
        if (k % 6 == 0) {
            i++;
            if (i == 8) {
                i = 0;
            }
        }
        
        /* 
        i++;
        if (i == 8) {
            i = 0;
        }
        */
        try {
            image = ImageIO.read(getClass().getResource("img/" + i + ".png"));
        } catch (IOException e) {
            System.out.println("Bird图片加载失败");
        }
    }
}