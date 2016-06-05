package GraphicHandler;

import Objects.Button;
import static Game.Game.WIDTH;

public class Assets {
    public static Button startButton;
    public static Button exitButton;

    public static void init() {
        startButton = new Button(WIDTH/2-100,150, ImageLoader.loadImage("/res/StartButton.png"));
        exitButton = new Button(WIDTH/2-100,250, ImageLoader.loadImage("/res/ExitButton.png"));

    }
}
