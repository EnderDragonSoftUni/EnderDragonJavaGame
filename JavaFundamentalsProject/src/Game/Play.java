package Game;

import GraphicHandler.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by kalin on 05.06.16.
 */
public class Play {

    private BufferedImage background = null;
    private ImageLoader imageLoader = new ImageLoader();
    private Game game;


    public void render(Graphics g){
        try {
            background = imageLoader.loadImage("/res/PlayGame.jpg");
        }catch (Exception e){

            e.printStackTrace();
        }

        g.drawImage(background, 0, 0, this.game);

    }
}
