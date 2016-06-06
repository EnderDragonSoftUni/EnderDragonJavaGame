package GraphicHandler;

import Objects.Player;
import javafx.scene.input.KeyCode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Niki on 6.6.2016 г..
 */
public class InputHandler implements KeyListener {

    public InputHandler(Canvas canvas) {
        canvas.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_RIGHT) {
            Player.isMovingRight = true;
            Player.isMovingLeft = false;
        } else if (code == KeyEvent.VK_LEFT) {
            Player.isMovingLeft = true;
            Player.isMovingRight = false;
        }
        if (code == KeyEvent.VK_SPACE && !Player.inAir){
            Player.gravity = -14;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_RIGHT) {
            Player.isMovingRight = false;
            Player.isMovingLeft = false;
        } else if (code == KeyEvent.VK_LEFT) {
            Player.isMovingLeft = false;
            Player.isMovingRight = false;
        }
    }
}
