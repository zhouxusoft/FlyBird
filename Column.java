import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Column {
    //柱子图片暂存区对象
    BufferedImage column;
    /**
     * 此类中共有两个柱子
     * x1,y1为柱子1的位置
     * x2,y2为柱子2的位置
     */ 
    public int x1, y1, x2, y2;

    public Column() {
        try {
            /**
             * 初始化柱子1、2的位置
             * 柱子的缺口高度随机
             * 当有= -350时柱子正好在中间，
             * Math.random()能给出一个0-1的随机数
             * Math.random() - 0.5 就能给出一个-0.5到0.5的数
             * (Math.ranom() - 0.5) * 240 就能给出一个-120到120之间的数
             * 这样柱子就能以中间为基准，随机向上下浮动不超过120的距离
             */
            x1 = 450;
            y1 = (int)(-350 - ((Math.random() - 0.5) * 240));
            x2 = 750;
            y2 = (int)(-350 - ((Math.random() - 0.5) * 240));
            //将柱子的图片赋给column
            column = ImageIO.read(getClass().getResource("img/column.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        /**
         * 执行此方法，管道会不断从右边向屏幕左边移动
         * 移出屏幕后会复位
         * 并且复位后会重新刷新缺口的位置
         */
        x1--;
        x2--;
        if (x1 == -100) {
            x1 = 500;
            y1 = (int) (-350 - ((Math.random() - 0.5) * 240));
        }
        if (x2 == -100) {
            x2 = 500;
            y2 = (int)(-350 - ((Math.random() - 0.5) * 240));
        }
    }
}
