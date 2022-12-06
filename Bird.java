import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Bird {
    //创建图片暂存区对象
    public BufferedImage image;
    //设置鸟的位置
    public int x;
    public int y;
    //用于修改小鸟的运动动画
    public int i = 0, k = 0;

    public Bird() {
        try {
            //初始化小鸟位置
            x = 120;
            y = 220;
            //将小鸟图片赋给图片暂存区对象
            image = ImageIO.read(getClass().getResource("img/0.png"));
        } catch (IOException e) {
            System.out.println("Bird图片加载失败");
        }
    }

    public void fly() {
        
        /**
         * 一个图片显示10次，这样小鸟飞的就会更慢
         * 通过修改赋值图片的路径，来达到8张图片循环播放的效果
         */
        k++;
        if (k % 10 == 0) {
            i++;
            if (i == 8) {
                i = 0;
            }
        }
        try {
            image = ImageIO.read(getClass().getResource("img/" + i + ".png"));
        } catch (IOException e) {
            System.out.println("Bird图片加载失败");
        }
    }
}