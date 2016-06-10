package GraphicHandler;

import Game.Game;
import Objects.Gift;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class GiftHandler {
    public static ArrayList<Gift> objects = new ArrayList<>();

    private static Random rand;
    private static int timeBetweenGifts = 0;

    public GiftHandler() {
        this.rand = new Random();
    }

    public void tick() {

        if (InputHandler.beginning == false) {
            if (timeBetweenGifts >= 30) {
                timeBetweenGifts = 0;
                addRandomGifts();
            } else {
                timeBetweenGifts++;
            }
        }

        for (int i = 0; i < objects.size(); i++) {
            Gift tempObject = objects.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        try {
            for (int i = 0; i < objects.size(); i++) {
                Gift tempObject = objects.get(i);

                tempObject.render(g);
            }
        } catch (Exception e) {

        }
    }

    public static void  clearAllGifts(){
        objects = new ArrayList<>();
    }

    public static void addObject(Gift object) {
        objects.add(object);
    }

    public static void removeObject(Gift object) {
        objects.remove(object);
    }

    public static void addRandomGifts() {
        int tempWidth = rand.nextInt(200);
        int tempX = rand.nextInt(Game.WIDTH - tempWidth);
        int tempY = rand.nextInt(200);

        objects.add(new Gift(tempX, tempY, 32, 32));
    }

    public static void addStartingGifts() {
        objects.add(new Gift(rand.nextInt(200), rand.nextInt(100), 32,32));

    }
}
