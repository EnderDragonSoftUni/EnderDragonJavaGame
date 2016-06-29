package GraphicHandler;

import Game.Game;
import Objects.Platform;

import java.awt.*;

/**
 * Created by Niki on 29.6.2016 г..
 */
public class LevelHandler {

    private static int currentLevel;
    private PlatformHandler platformHandler;
    private GiftHandler giftHandler;

    public LevelHandler(PlatformHandler platformHandler, GiftHandler giftHandler) {
        currentLevel = 1;
        this.platformHandler = platformHandler;
        this.giftHandler = giftHandler;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public static void nextLevel() {
        LevelHandler.currentLevel++;
    }

    public static void setCurrentLevel(int currentLevel) {
        LevelHandler.currentLevel = currentLevel;
    }

    public void tick() {
        if (this.currentLevel == 1) {
            Platform.setImg(Assets.iceberg);
        }else if (this.currentLevel == 1){

            Platform.setImg(Assets.iceberg);
        } else {
            Platform.setImg(Assets.iceberg);
        }
    }

    public void render(Graphics g) {
        if (this.currentLevel == 1) {
            g.drawImage(Assets.background, 0, 0, Game.WIDTH, Game.WIDTH, null);
        } else if (this.getCurrentLevel() == 2) {
            g.setColor(Color.black);
            g.fillRect(0, 0,  Game.WIDTH, Game.WIDTH);
        } else {
            g.setColor(Color.red);
            g.fillRect(0, 0,  Game.WIDTH, Game.WIDTH);
        }
    }

}
