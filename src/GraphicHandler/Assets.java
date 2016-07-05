package GraphicHandler;

import Game.Game;
import Objects.Button;
import java.awt.Image;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Assets {

    public static Button startButton;
    public static int startButtonX = Game.WIDTH / 2 - 100;
    public static int startButtonY = 150;

    public static Button exitButton;
    public static int endButtonX = Game.WIDTH / 2 - 100;
    public static int endButtonY = 300;

    public static Button backButton;
    public static int backButtonX = Game.WIDTH / 2 - 100;
    public static int backButtonY = 300;

    public static Button tryAgainButton;
    public static int tryAgainButtonX = Game.WIDTH / 2 - 100;
    public static int tryAgainButtonY = 300;

    public static BufferedImage background;
    public static BufferedImage gameLogo;
    public static BufferedImage gameOver;
    public static BufferedImage iceberg;
    public static BufferedImage hiscore;
    public static ArrayList<BufferedImage> levelBackgrounds;

    public static SpriteSheet player;
    public static SpriteSheet goldCoin;
    public static SpriteSheet silverCoin;
    public static SpriteSheet copperCoin;

    public static void init() throws IOException {
        startButton = new Button(startButtonX, startButtonY, ImageLoader.loadImage("/PlayButton.png"));
        exitButton = new Button(endButtonX, endButtonY, ImageLoader.loadImage("/ExitButton.png"));
        backButton = new Button(backButtonX, backButtonY, ImageLoader.loadImage("/BackButton.png"));
        tryAgainButton = new Button(tryAgainButtonX, tryAgainButtonY, ImageLoader.loadImage("/TryAgainButton.png"));

        background = ImageLoader.loadImage("/background.png");
        gameLogo = ImageLoader.loadImage("/logo.png");
        gameOver = ImageLoader.loadImage("/gameover.png");
        iceberg = ImageLoader.loadImage("/Iceberg.png");
        hiscore = ImageLoader.loadImage("/hiscore-logo.png");
        levelBackgrounds = new ArrayList<>();
        createLevelsBackgrounds();

        player = new SpriteSheet(ImageLoader.loadImage("/sprite.png"));
        goldCoin = new SpriteSheet(ImageLoader.loadImage("/coin_gold.png"));
        silverCoin = new SpriteSheet(ImageLoader.loadImage("/coin_silver.png"));
        copperCoin = new SpriteSheet(ImageLoader.loadImage("/coin_copper.png"));

    }

    private static void createLevelsBackgrounds() throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        String propFileName = "properti/background.properties";
        InputStream inputStream = Assets.class.getClassLoader().getResourceAsStream(propFileName);
        if (inputStream != null) {
            properties.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }
        for (int i = 0; i < properties.size(); i++) {
            levelBackgrounds.add(ImageLoader.loadImage(properties.getProperty(String.valueOf(i))));
        }
        inputStream.close();
    }
}
