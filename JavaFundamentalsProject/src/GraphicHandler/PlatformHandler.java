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
    public static  ArrayList<Platform> objects = new ArrayList<>();

    private static Random rand;
    private static int timeBetweenPlatforms = 0;

    public PlatformHandler(){
        this.rand = new Random();
    }

    public void tick() {

        if (timeBetweenPlatforms == 30){
            timeBetweenPlatforms=0;
            addRandomPlatform();
        }else{
            timeBetweenPlatforms++;
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

    public static void addObject(Platform object) {
        objects.add(object);
    }

    public static void removeObject(Platform object) {
        objects.remove(object);
    }

    public static void addRandomPlatform() {
        int tempWidth = rand.nextInt(200)+100;
        int tempX = rand.nextInt(Game.WIDTH-tempWidth);
        int tempY = -20;
//        boolean intersects = false;
//        for (Platform object : objects) {
//            if (!object.getBounds().intersects(new Rectangle(tempX, tempY, tempWidth, 20))){
//                intersects = true;
//            }
//        }
//        if (!intersects){
//            objects.add(new Platform(tempX,tempY,tempWidth,20));
//        }
        objects.add(new Platform(tempX, tempY, tempWidth, 20));
    }
}
