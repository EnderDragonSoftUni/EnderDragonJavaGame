package GraphicHandler;

import Game.Game;
import Objects.Button;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Assets {

    public static Button startButton;
    public static int startButtonX = Game.WIDTH / 2 - 100;
    public static int startButtonY = 120;

    public static Button shopButton;
    public static int shopButtonX = Game.WIDTH / 2 - 100;
    public static int shopButtonY = 220;

    public static Button exitButton;
    public static int endButtonX = Game.WIDTH / 2 - 100;
    public static int endButtonY = 320;

    public static Button backButton;
    public static int backButtonX = Game.WIDTH / 2 - 100;
    public static int backButtonY = 300;

    public static Button shopBackButton;
    public static int shopBackButtonX = Game.WIDTH / 2 - 100;
    public static int shopBackButtonY = 350;

    public static Button tryAgainButton;
    public static int tryAgainButtonX = Game.WIDTH / 2 - 100;
    public static int tryAgainButtonY = 300;

    public static Button buyItemOneButton;
    public static int buyItemOneButtonX = Game.WIDTH / 2 - 250;
    public static int buyItemOneButtonY = 270;

    public static Button buyItemTwoButton;
    public static int buyItemTwoButtonX = Game.WIDTH / 2 - 50;
    public static int buyItemTwoButtonY = 270;

    public static Button buyItemThreeButton;
    public static int buyItemThreeButtonX = Game.WIDTH / 2 + 150;
    public static int buyItemThreeButtonY = 270;

    public static BufferedImage background;
    public static BufferedImage gameLogo;
    public static BufferedImage gameOver;
    public static BufferedImage iceberg;
    public static BufferedImage tile2;
    public static BufferedImage hiscore;
    public static BufferedImage nakovImage;
    public static BufferedImage wizImage;
    public static BufferedImage zombieImage;
    public static ArrayList<BufferedImage> levelBackgrounds;

    public static SpriteSheet player;
    public static SpriteSheet wizard;
    public static SpriteSheet nakov;
    public static SpriteSheet zombie;

    public static SpriteSheet goldCoin;
    public static SpriteSheet silverCoin;
    public static SpriteSheet copperCoin;

    public static void init() throws IOException {
        startButton = new Button(startButtonX, startButtonY, ImageLoader.loadImage("/PlayButton.png"));
        exitButton = new Button(endButtonX, endButtonY, ImageLoader.loadImage("/ExitButton.png"));
        backButton = new Button(backButtonX, backButtonY, ImageLoader.loadImage("/BackButton.png"));
        shopBackButton = new Button(shopBackButtonX, shopBackButtonY, ImageLoader.loadImage("/BackButton.png"));
        tryAgainButton = new Button(tryAgainButtonX, tryAgainButtonY, ImageLoader.loadImage("/TryAgainButton.png"));

        buyItemOneButton = new Button(buyItemOneButtonX, buyItemOneButtonY, 100, 40, ImageLoader.loadImage
                ("/SelectButton.png"));
        buyItemTwoButton = new Button(buyItemTwoButtonX, buyItemTwoButtonY, 100, 40, ImageLoader.loadImage
                ("/SelectButton.png"));
        buyItemThreeButton = new Button(buyItemThreeButtonX, buyItemThreeButtonY, 100, 40, ImageLoader.loadImage
                ("/SelectButton.png"));

        shopButton = new Button(shopButtonX, shopButtonY, ImageLoader.loadImage("/ShopButton.png"));


        background = ImageLoader.loadImage("/background.png");
        gameLogo = ImageLoader.loadImage("/logo.png");
        gameOver = ImageLoader.loadImage("/gameover.png");
        iceberg = ImageLoader.loadImage("/Iceberg.png");
        tile2 = ImageLoader.loadImage("/tile2.jpg");
        hiscore = ImageLoader.loadImage("/hiscore-logo.png");

        wizImage = ImageLoader.loadImage("/wizImage.png");
        nakovImage = ImageLoader.loadImage("/NakovImg.png");
        zombieImage = ImageLoader.loadImage("/zombieImg.png");

        levelBackgrounds = new ArrayList<>();
        createLevelsBackgrounds();

        player = new SpriteSheet(ImageLoader.loadImage("/sprite.png"));
        wizard = new SpriteSheet(ImageLoader.loadImage("/wizard.png"));
        nakov = new SpriteSheet(ImageLoader.loadImage("/Nakov.png"));
        zombie = new SpriteSheet(ImageLoader.loadImage("/spriteZombie.png"));

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
