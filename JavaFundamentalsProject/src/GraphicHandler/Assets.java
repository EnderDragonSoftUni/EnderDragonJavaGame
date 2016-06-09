package GraphicHandler;

import Game.Game;
import Objects.Button;

import java.awt.image.BufferedImage;

public class Assets {
    public static Button startButton;
    public static int startButtonX = Game.WIDTH/2-100;
    public static int startButtonY = 150;

    public static Button exitButton;
    public static int endButtonX = Game.WIDTH/2-100;
    public static int endButtonY = 300;

    public static Button backButton;
    public static int backButtonX = Game.WIDTH/2-100;
    public static int backButtonY = 300;

    public static Button tryAgainButton;
    public static int tryAgainButtonX = Game.WIDTH/2-100;
    public static int tryAgainButtonY = 300;

    public static BufferedImage background;
    public static BufferedImage gameLogo;
    public static BufferedImage gameOver;
    public static BufferedImage iceberg;

    public static SpriteSheet player;


    public static void init() {
        startButton = new Button(startButtonX,startButtonY, ImageLoader.loadImage("/PlayButton.png"));
        exitButton = new Button(endButtonX,endButtonY, ImageLoader.loadImage("/ExitButton.png"));
        backButton = new Button(backButtonX, backButtonY, ImageLoader.loadImage("/BackButton.png"));
        tryAgainButton = new Button(tryAgainButtonX, tryAgainButtonY, ImageLoader.loadImage("/TryAgainButton.png"));

        background = ImageLoader.loadImage("/background.png");
        gameLogo = ImageLoader.loadImage("/logo.png");
        gameOver = ImageLoader.loadImage("/gameover.png");
        iceberg = ImageLoader.loadImage("/Iceberg.png");

        player = new SpriteSheet(ImageLoader.loadImage("/sprite.png"));
    }
}
