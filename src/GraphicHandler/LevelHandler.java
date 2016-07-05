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
        currentLevel = 0;
        this.platformHandler = platformHandler;
        this.giftHandler = giftHandler;
    }

    public static int getCurrentLevel() {
        return currentLevel;
    }

    public static void nextLevel() {
        LevelHandler.currentLevel++;
    }

    public static void setCurrentLevel(int currentLevel) {
        LevelHandler.currentLevel += currentLevel;
    }

    public void tick() {
        if (this.currentLevel == 1) {
            Platform.setImg(Assets.iceberg);
        }else if (currentLevel == 1){

            Platform.setImg(Assets.iceberg);
        } else {
            Platform.setImg(Assets.iceberg);
        }
    }

    public void render(Graphics g) {
        switch (currentLevel) {
            default:
                int levelSize = Assets.levelBackgrounds.size();
                g.drawImage(Assets.levelBackgrounds.get(currentLevel % levelSize), 0, 0, Game.WIDTH, Game.WIDTH, null);
                break;
        }
     /*   } else if (getCurrentLevel() == 2) {
            g.setColor(Color.black);
            g.fillRect(0, 0,  Game.WIDTH, Game.WIDTH);
        } else {
            g.setColor(Color.red);
            g.fillRect(0, 0,  Game.WIDTH, Game.WIDTH);
        }*/
    }

}
