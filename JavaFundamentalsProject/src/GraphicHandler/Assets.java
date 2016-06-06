package GraphicHandler;

import Game.Game;
import Objects.BigPlatform;
import Objects.Button;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {
    public static Button startButton;
    public static int startButtonX = Game.WIDTH/2-100;
    public static int startButtonY = 150;

    public static Button exitButton;
    public static int endButtonX = Game.WIDTH/2-100;
    public static int endButtonY = 300;

    public static BufferedImage background;
    public static BufferedImage gameLogo;
    public static SpriteSheet player;

    public static void init() {
        startButton = new Button(startButtonX,startButtonY, ImageLoader.loadImage("/PlayButton.png"));
        exitButton = new Button(endButtonX,endButtonY, ImageLoader.loadImage("/ExitButton.png"));

        background = ImageLoader.loadImage("/background.png");
        gameLogo = ImageLoader.loadImage("/logo.png");

        player = new SpriteSheet(ImageLoader.loadImage("/sprite.png"));
    }
}
