package GraphicHandler;
import Objects.Button;
import Game.Game;

public class Assets {
    public static Button startButton;
    public static int startButtonX = Game.WIDTH/2-100;
    public static int startButtonY = 150;

    public static Button exitButton;
    public static int endButtonX = Game.WIDTH/2-100;
    public static int endButtonY = 250;

    public static void init() {
        startButton = new Button(startButtonX,startButtonY, ImageLoader.loadImage("/res/StartButton.png"));
        exitButton = new Button(endButtonX,endButtonY, ImageLoader.loadImage("/res/ExitButton.png"));

    }

}
