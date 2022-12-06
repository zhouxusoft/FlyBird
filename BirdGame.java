import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Font;
import java.io.*;

public class BirdGame extends JPanel {
    // 图片暂存区对象
    public BufferedImage bg, simage, gimage, s, g;
    // 声明一个Ground对象
    public Ground ground;
    // 声明一个Bird对象
    public Bird bird;
    // 声明一个键盘点击事件
    public KeyClick keyClick;
    // 添加一个柱子对象，里面包含两根柱子
    public Column column1;
    // 创建游戏状态
    public int state;// 游戏状态
    public final int START = 0;// 游戏开始
    public final int RUNNING = 1;// 游戏进行中
    public final int GAMEOVER = 2;// 游戏结束
    public int speed = 2, score = 0, maxsc = 0;// 游戏结束
    public String showsc, showmaxsc; // sc记录分数 showsc将sc转换为字符串打印在窗口上

    public BirdGame() {
        try {
            // 创建一个文件来记录最高分
            File f1 = new File("最高分.txt");// 相对路径，如果没有前面的src，就在当前目录创建文件
            /**
             * 若文件不存在则自动创建
             * 并在创建的同时写入值0 初始默认最大值为0 并保证文件中有东西可供读取 后面就不会报错
             */
            if (!f1.exists()) {
                try {
                    f1.createNewFile();
                    PrintWriter pw = new PrintWriter(f1);
                    pw.write("0");
                    pw.close();
                } catch (Exception e) {
                    System.out.println("文件创建成功");
                }
            }
            /**
             * 创建文件读取对象
             * FileReader fr = new FileReader(f1);
             * BufferedReader br = new BufferedReader(fr);
             * maxsc = br.readLine();
             */
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            maxsc = Integer.parseInt(br.readLine());
            br.close();
            System.out.println(maxsc);
            // 默认游戏状态为待开始
            state = START;
            // 将图片赋给图片暂存区对象
            bg = ImageIO.read(getClass().getResource("img/bg.png"));
            simage = ImageIO.read(getClass().getResource("img/start.png"));
            gimage = ImageIO.read(getClass().getResource("img/gameover.png"));
            ground = new Ground(); // 实例化地板对象
            bird = new Bird(); // 实例化小鸟对象
            column1 = new Column(); // 实例化柱子对象
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // 设置输出分数的字体
        g.setFont(new Font("微软雅黑", Font.BOLD, 22));
        // 添加背景图片
        g.drawImage(bg, 0, 0, null);
        // 添加两根柱子
        g.drawImage(column1.column, column1.x1, column1.y1, null);
        g.drawImage(column1.column, column1.x2, column1.y2, null);
        // 添加地面
        g.drawImage(ground.gd, ground.x, ground.y, null);
        // 添加鸟
        g.drawImage(bird.image, bird.x, bird.y, null);
        // 添加开始菜单
        g.drawImage(s, 0, 0, null);
        // 添加结束菜单
        g.drawImage(this.g, 0, 0, null);
        // 打印分数
        g.drawString(showsc, 10, 565);
        g.drawString(showmaxsc, 10, 600);

    }

    public void StartGame() {
        while (true) {
            switch (state) {
                case START:
                    s = simage; // 开始界面时，将输出的界面s，赋值为开始界面的图片
                    g = null; // 开始时将结束界面的图片设置为空，就不会显示
                    score = 0;
                    showsc = "";
                    showmaxsc = "";
                    // 开始游戏时地面开始运动
                    ground.step();
                    // 开始游戏时鸟开始飞
                    bird.fly();
                    // 初始化鸟的位置
                    bird.x = 120;
                    bird.y = 220;
                    // 初始化柱子的位置
                    column1.x1 = 450;
                    column1.x2 = 750;
                    break;
                case RUNNING:
                    s = null; // 运行时，将开始图片隐藏
                    // 地面和竹子也开始运动
                    ground.step();
                    column1.run();
                    // 小鸟根据速度进行位移
                    bird.y = bird.y + speed;
                    // 鸟开始飞
                    bird.fly();
                    // 开始计算得分
                    showsc = "得分：" + String.valueOf(score);
                    showmaxsc = "最高分：" + String.valueOf(maxsc);
                    // 根据y轴位置来判断鸟是否撞击到地面或者天空
                    if (bird.y < 0 || bird.y > 460) {
                        state = GAMEOVER;
                    }
                    /**
                     * 判断鸟是否撞击到了柱子
                     * 先判断鸟是否遇上了柱子
                     * 只有鸟和柱子的x轴处于同一区域时才有可能会撞到
                     * 当柱子和鸟x轴方向上重叠时，只有鸟在柱子缝隙中才能存活
                     */
                    if (column1.x1 < bird.x + 56 && bird.x - 78 < column1.x1) {
                        score++; // 经过柱子不断加分
                        showsc = "得分：" + String.valueOf(score); // 拼接得分
                        if (bird.y < column1.y1 + 500 || bird.y + 48 > column1.y1 + 700) {
                            state = GAMEOVER;
                        }
                    }
                    if (column1.x2 < bird.x + 56 && bird.x - 78 < column1.x2) {
                        score++;// 经过柱子不断加分
                        showsc = "得分：" + String.valueOf(score);// 拼接得分
                        if (bird.y < column1.y2 + 530 || bird.y + 48 > column1.y2 + 680) {
                            state = GAMEOVER;
                        }
                    }
                    break;
                case GAMEOVER:
                /**
                 * 判断结束时分数是否大于最高分
                 * 若大于最高分则修改文件中的最高分
                 * 修改方式：
                 * 将原文件删除，新建一个写入新的最高分
                 */
                    if (score > maxsc) {
                        maxsc = score;
                        File f1 = new File("最高分.txt");
                        if (f1.exists()) {
                            f1.delete();
                            try {
                                f1.createNewFile();
                                PrintWriter pw = new PrintWriter(f1);
                                pw.write(String.valueOf(maxsc));
                                pw.close();
                            } catch (Exception e) {
                                System.out.println("文件创建成功");
                            }
                        }
                    }
                    g = gimage; // 结束时，gameover图片显示
                    // 把鸟的图片设为空，从而在GAMEOVER时隐藏小鸟
                    bird.image = null;
                    break;
            }
            // 重新绘制
            repaint();
            // 线程休眠，控制游戏速度
            try {
                Thread.sleep(1000 / 150);// 线程控制速度
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
