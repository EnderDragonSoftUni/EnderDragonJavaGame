package Game.levels;

import Game.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Handler;

import GraphicHandler.Assets;
import Display.Window;
import GraphicHandler.PlatformHandler;
import Sound.Sound;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChooseLevel implements MouseMotionListener, MouseListener {

    private Game game;
    private PlatformHandler platformHandler;
    private boolean isOver;
    private Window window;

    public ChooseLevel(Game game, PlatformHandler platformHandler, Window window) {
        this.platformHandler = platformHandler;
        this.game = game;
        this.isOver = false;
        this.window = window;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (game.gameState == Game.STATE.Menu) {
            //Sound.playSound("res/audio/WOW.wav");
            //Play Button
            if (mouseOver(mx, my, Assets.startButtonX, Assets.startButtonY, 200, 80)) {
                //game.gameState = Game.STATE.Game;
                isOver = true;
                game.repaint();

                return;
            }

            //Quit Button
            if (mouseOver(mx, my, Assets.endButtonX, Assets.endButtonY, 200, 80)) {
                isOver = true;
                game.repaint();
            }
        }

        if (game.gameState == Game.STATE.End) {
            if (mouseOver(mx, my, Assets.tryAgainButtonX, Assets.tryAgainButtonY, 200, 80)) {
                Game.gameState = Game.STATE.Menu;

            }
        }

    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void tick() {

    }

    public void render(Graphics g) {

        
        
        Color myColour = new Color(34, 139, 34, 255);
        g.setColor(myColour);
        
        g.fillRect(0, 0, game.getWidth(), game.getHeight());
        int j = 0;
        int setHeight = (game.getHeight() / 2) - 70; 
        for (int i = 0; i < Assets.levelBackgrounds.size(); i++) {
            myColour = new Color(220,220,220, 255);
            g.setColor(myColour);
            if (isOver) {
                 g.fillRect(15 + j, setHeight -5, 140, 140);
                 isOver = false;
            }else{
                g.fillRect(25 + j, setHeight + 5, 140, 140);
                
            }
            
            

            g.drawImage(Assets.levelBackgrounds.get(i), 20 + j, setHeight, 140, 140, null);

            j += 150;

        }
       
       
        /*
        if (game.gameState == Game.STATE.ChooseLevel) {

            g.drawImage(Assets.gameLogo, Game.WIDTH / 3 - 80, 20, 380, 70, null);
            Assets.startButton.render(g);
            Assets.exitButton.render(g);

        } else if (Game.gameState == Game.STATE.End){
            g.drawImage(Assets.gameOver, 170, 40, 320, 100, null);
            Assets.tryAgainButton.render(g);
        }
        
         */
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

}
