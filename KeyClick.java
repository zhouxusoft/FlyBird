import java.awt.event.*;
public class KeyClick implements KeyListener{
    BirdGame birdGame;
    /**
     * 通过键盘事件来进行游戏或修改游戏状态
     * 传入birdGame对象，以便于修改游戏状态
     * @param birdGame
     */
    public KeyClick(BirdGame birdGame) {
        this.birdGame = birdGame;
    }
    /**
     * 键盘按下、按住、松开时发生的事件
     * Space键值为32，Enter键值为10
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 32) {
            this.birdGame.speed = -1; //按下时修改小鸟的运动状态 向上
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 32) {
            this.birdGame.speed = 1;  //松开时恢复小鸟的运动状态 向下
        }
        /**
         * 当游戏结束时，按下回车重新回到开始界面
         * 当游戏未开始时，按下回车开始游戏
         */
        if (e.getKeyCode() == 10) {
            if (this.birdGame.state == this.birdGame.GAMEOVER) {
                this.birdGame.state = this.birdGame.START;
            }
            else if (this.birdGame.state == this.birdGame.START) {
                this.birdGame.state = this.birdGame.RUNNING;
            }
        }
    }
    

}
