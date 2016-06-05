package Game;

<<<<<<< HEAD
import GraphicHandler.Assets;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
            //Play Button
            if (mouseOver(mx, my, Assets.startButtonX, Assets.startButtonY, 200, 50)) {
                game.gameState = Game.STATE.Game;
                return;
            }

            //Quit Button
            if (mouseOver(mx, my, Assets.endButtonX, Assets.endButtonY, 200, 50)) {
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
            g.setColor(Color.GRAY);
            g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

            Assets.startButton.render(g);
            Assets.exitButton.render(g);
        }
=======
import GraphicHandler.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by kalin on 05.06.16.
 */
public class Menu {
    private BufferedImage background = null;
    private ImageLoader imageLoader = new ImageLoader();
    private Game game;


    public void render(Graphics g){
        try {
            background = imageLoader.loadImage("/res/back.png");
        }catch (Exception e){

            e.printStackTrace();
        }

        Font fnt = new Font("Georgia", Font.BOLD, 48);
        g.drawImage(background, 0, 0, this.game);
        g.setFont(fnt);
        g.setColor(Color.green);
        g.drawString("SoftUni Tower", Game.WIDTH / 3, 100);
>>>>>>> 36b636895c4786954dd5105dd426b5b376364bf6
    }
}
