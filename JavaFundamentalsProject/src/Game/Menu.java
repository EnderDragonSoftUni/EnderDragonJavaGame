package Game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import GraphicHandler.Assets;
import Sound.Sound;




/**
 * Created by Niki on 5.6.2016 г..
 */
public class Menu extends MouseAdapter {
    private Game game;

    public Menu(Game game) {

        this.game = game;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (game.gameState == Game.STATE.Menu) {
            Sound.playSound("res/audio/gong.wav").join();
            //Play Button
            if (mouseOver(mx, my, Assets.startButtonX, Assets.startButtonY, 200, 80)) {
                game.gameState = Game.STATE.Game;

                return;
            }

            //Quit Button
            if (mouseOver(mx, my, Assets.endButtonX, Assets.endButtonY, 200, 80)) {
                System.exit(1);
            }
        }

        if (game.gameState == Game.STATE.Game) {

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
        if (game.gameState == Game.STATE.Menu) {

            g.drawImage(Assets.gameLogo, Game.WIDTH / 3 - 80, 20, 380, 70, null);
            Assets.startButton.render(g);
            Assets.exitButton.render(g);

        }
    }
}
