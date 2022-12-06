import java.awt.event.*;
public class KeyClick implements KeyListener{
    BirdGame birdGame;

    public KeyClick(BirdGame birdGame) {
        this.birdGame = birdGame;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == 32) {
            System.out.println(e.getKeyCode());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 32) {
            this.birdGame.speed = -2;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 32) {
            
            this.birdGame.speed = 2;
        }
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
