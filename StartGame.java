import javax.swing.*;
public class StartGame {
    public static void main(String[] args) {
        //设置窗口标题
        JFrame frame = new JFrame("抽搐的傻鸟"); 
        //设置窗口大小
        frame.setSize(445, 650);    
        //设置窗口位置默认居中
        frame.setLocationRelativeTo(null);  
        //设置窗口关闭程序运行结束
        frame.setDefaultCloseOperation(3); 
        //new一个BirdGame对象，并加入到窗口当中
        BirdGame birdGame = new BirdGame();
        frame.add(birdGame);
        //给窗口添加键盘监听事件
        frame.addKeyListener(new KeyClick(birdGame));
        //窗体大小不可改变
        frame.setResizable(false); 
        //设置窗口可见
        frame.setVisible(true);
        //进行birdGame中的开始游戏方法
        birdGame.StartGame();
    }
}