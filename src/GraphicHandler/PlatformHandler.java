package GraphicHandler;

import Game.Game;
import Objects.Platform;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Niki on 7.6.2016 г..
 */
public class PlatformHandler {
    public static ArrayList<Platform> objects = new ArrayList<>();

    private static Random rand;
    private static int timeBetweenPlatforms = 0;

    public PlatformHandler() {
        this.rand = new Random();
    }

    public void tick() {

        if (InputHandler.beginning == false) {
            if (timeBetweenPlatforms >= 30) {
                timeBetweenPlatforms = 0;
                addRandomPlatform();
            } else {
                timeBetweenPlatforms++;
            }
        }

        for (int i = 0; i < objects.size(); i++) {
            Platform tempObject = objects.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        try {
            for (int i = 0; i < objects.size(); i++) {
                Platform tempObject = objects.get(i);

                tempObject.render(g);
            }
        } catch (Exception e) {

        }
    }
    
    public static void  clearAllPlatforms(){
        objects = new ArrayList<>();
    }

    public static void addObject(Platform object) {
        objects.add(object);
    }

    public static void removeObject(Platform object) {
        objects.remove(object);
    }

    public static void addRandomPlatform() {
        int tempWidth = rand.nextInt(200) + 100;
        int tempX = rand.nextInt(Game.WIDTH - tempWidth);
        int tempY = -20;

        objects.add(new Platform(tempX, tempY, tempWidth, 20));
    }

    public static void addStartingPlatforms() {
        objects.add(new Platform(0, 400, 700, 60));
        objects.add(new Platform(200, 270, 200, 20));
        objects.add(new Platform(100, 120, 200, 20));
        objects.add(new Platform(200, -20, 200, 20));
    }
}
