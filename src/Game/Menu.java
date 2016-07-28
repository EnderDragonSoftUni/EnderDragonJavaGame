package Game;

import GraphicHandler.Assets;
import GraphicHandler.PlatformHandler;
import Objects.Player;

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

        if (Game.gameState == Game.STATE.Menu) {
            if (mouseOver(mx, my, Assets.startButton.getX(), Assets.startButton.getY(), 200, 80)) {
                Game.gameState = Game.STATE.Game;
                game.createPlayer();

                return;
            }

            if (mouseOver(mx, my, Assets.shopButton.getX(), Assets.shopButton.getY(), 200, 80)) {
                Game.gameState = Game.STATE.Shop;

                return;
            }

            if (mouseOver(mx, my, Assets.highScoreButton.getX(), Assets.highScoreButton.getY(), 200, 80)) {
                Game.gameState = Game.STATE.HighScore;
                return;
            }

            //Quit Button
            if (mouseOver(mx, my, Assets.exitButton.getX(), Assets.exitButton.getY(), 200, 80)) {
                System.exit(1);
            }
        }

        if (Game.gameState == Game.STATE.End) {
            if (mouseOver(mx, my, Assets.tryAgainButton.getX(), Assets.tryAgainButton.getY(), 200, 80)) {
                Game.gameState = Game.STATE.Menu;
                game.setScore(0);
            }
        }

        if (Game.gameState == Game.STATE.Shop) {
            if (mouseOver(mx, my, Assets.shopBackButton.getX(), Assets.shopBackButton.getY(), 200, 80)) {
                Game.gameState = Game.STATE.Menu;

                return;
            }
            if (mouseOver(mx, my, Assets.buyItemOneButton.getX(), Assets.buyItemOneButton.getY(), 100, 40)) {
                Game.item1unlocked = true;
                Game.item2unlocked = false;
                Game.item3unlocked = false;

                Player.img = Assets.wizard;

                return;
            }
            if (mouseOver(mx, my, Assets.buyItemTwoButton.getX(), Assets.buyItemTwoButton.getY(), 100, 40)) {
                Game.item2unlocked = true;
                Game.item1unlocked = false;
                Game.item3unlocked = false;

                Player.img = Assets.nakov;

                return;
            }
            if (mouseOver(mx, my, Assets.buyItemThreeButton.getX(), Assets.buyItemThreeButton.getY(), 100, 40)) {
                Game.item3unlocked = true;
                Game.item2unlocked = false;
                Game.item1unlocked = false;

                Player.img = Assets.zombie;

                return;
            }
        }

        if (Game.gameState == Game.STATE.HighScore) {
            if (mouseOver(mx, my, Assets.shopBackButton.getX(), Assets.shopBackButton.getY(), 200, 80)) {
                Game.gameState = Game.STATE.Menu;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            return my > y && my < y + height;
        } else {
            return false;
        }
    }

    public void tick() {

    }

    public void render(Graphics g) {
        if (Game.gameState == Game.STATE.Menu) {

            g.drawImage(Assets.gameLogo, Game.WIDTH / 3 - 80, 20, 380, 70, null);
            Assets.startButton.render(g);
            Assets.exitButton.render(g);
            Assets.shopButton.render(g);
            Assets.highScoreButton.render(g);

        } else if (Game.gameState == Game.STATE.End) {
            g.drawImage(Assets.gameOver, 170, 40, 320, 100, null);
            Assets.tryAgainButton.render(g);

            Font font = new Font("Calibri", Font.BOLD, 40);
            g.setFont(font);
            g.setColor(Color.BLUE);
            g.drawString(String.format("You lost with a score of: %d", this.game.getScore()), Game.WIDTH / 5 - 40, 250);
        } else if (Game.gameState == Game.STATE.Shop) {
            Assets.shopBackButton.render(g);

            g.drawString(String.format("Coins: %s", Game.coins), 10, 20);

            g.drawImage(Assets.wizImage, Assets.buyItemOneButton.getX() - 20, Assets.buyItemOneButton.getY() - 130,
                    null);
            g.drawImage(Assets.nakovImage, Assets.buyItemTwoButton.getX() - 10, Assets.buyItemTwoButton.getY() - 130,
                    null);
            g.drawImage(Assets.zombieImage, Assets.buyItemThreeButton.getX() - 10, Assets.buyItemThreeButton.getY() -
                    130, null);

            if (!Game.item1unlocked) {
                Assets.buyItemOneButton.render(g);
            }
            if (!Game.item2unlocked) {
                Assets.buyItemTwoButton.render(g);
            }
            if (!Game.item3unlocked) {
                Assets.buyItemThreeButton.render(g);
            }

        } else if (Game.gameState == Game.STATE.HighScore) {
            Game.currentScore.render(g);
            Assets.shopBackButton.render(g);
        }
    }
}
