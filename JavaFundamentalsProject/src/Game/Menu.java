package Game;

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
    }
}
