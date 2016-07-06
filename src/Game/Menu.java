package Game;

import GraphicHandler.Assets;
import GraphicHandler.PlatformHandler;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Created by Niki on 5.6.2016 г..
 */
public class Menu extends MouseAdapter {
    private Game game;
    private PlatformHandler platformHandler;

    public Menu(Game game, PlatformHandler platformHandler) {
        this.platformHandler = platformHandler;
        this.game = game;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (game.gameState == Game.STATE.Menu) {
            if (mouseOver(mx, my, Assets.startButton.getX(), Assets.startButton.getY(), 200, 80)) {
                game.gameState = Game.STATE.Game;

                return;
            }

            if (mouseOver(mx, my, Assets.shopButton.getX(), Assets.shopButton.getY(), 200, 80)) {
                game.gameState = Game.STATE.Shop;

                return;
            }

            //Quit Button
            if (mouseOver(mx, my, Assets.exitButton.getX(), Assets.exitButton.getY(), 200, 80)) {
                System.exit(1);
            }
        }

        if (game.gameState == Game.STATE.End) {
            if (mouseOver(mx, my, Assets.tryAgainButton.getX(), Assets.tryAgainButton.getY(), 200, 80)) {
                Game.gameState = Game.STATE.Menu;
                game.createPlayer();
            }
        }

        if (game.gameState == Game.STATE.Shop) {
            if (mouseOver(mx, my, Assets.shopBackButton.getX(), Assets.shopBackButton.getY(), 200, 80)) {
                game.gameState = Game.STATE.Menu;

                return;
            }
            if (mouseOver(mx, my, Assets.buyItemOneButton.getX(), Assets.buyItemOneButton.getY(), 100, 40)) {
                game.item1unlocked = true;

                return;
            }
            if (mouseOver(mx, my, Assets.buyItemTwoButton.getX(), Assets.buyItemTwoButton.getY(), 100, 40)) {
                game.item2unlocked = true;

                return;
            }
            if (mouseOver(mx, my, Assets.buyItemThreeButton.getX(), Assets.buyItemThreeButton.getY(), 100, 40)) {
                game.item3unlocked = true;

                return;
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
        if (game.gameState == Game.STATE.Menu) {

            g.drawImage(Assets.gameLogo, Game.WIDTH / 3 - 80, 20, 380, 70, null);
            Assets.startButton.render(g);
            Assets.exitButton.render(g);
            Assets.shopButton.render(g);

        } else if (Game.gameState == Game.STATE.End){
            g.drawImage(Assets.gameOver, 170, 40, 320, 100, null);
            Assets.tryAgainButton.render(g);
        } else if (game.gameState == Game.STATE.Shop) {
            Assets.shopBackButton.render(g);

            if (!game.item1unlocked){
                Assets.buyItemOneButton.render(g);
            }
            if (!game.item2unlocked){
                Assets.buyItemTwoButton.render(g);
            }
            if (!game.item3unlocked){
                Assets.buyItemThreeButton.render(g);
            }

        }
    }
}
