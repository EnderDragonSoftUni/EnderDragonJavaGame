package GraphicHandler;

import Game.Game;
import Objects.Gifts;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class GiftsHandler {
    public static ArrayList<Gifts> objects = new ArrayList<>();

    private static Random rand;
    private static int timeBetweenGifts = 0;

    public GiftsHandler() {
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
            Gifts tempObject = objects.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        try {
            for (int i = 0; i < objects.size(); i++) {
                Gifts tempObject = objects.get(i);

                tempObject.render(g);
            }
        } catch (Exception e) {

        }
    }
    
    public static void  clearAllGifts(){
        objects = new ArrayList<>();
    }

    public static void addObject(Gifts object) {
        objects.add(object);
    }

    public static void removeObject(Gifts object) {
        objects.remove(object);
    }

    public static void addRandomGifts() {
        int tempWidth = rand.nextInt(200);
        int tempX = rand.nextInt(Game.WIDTH - tempWidth);
        int tempY = rand.nextInt(200);

        objects.add(new Gifts(tempX, tempY, 32, 32));
    }

    public static void addStartingGifts() {
       objects.add(new Gifts(rand.nextInt(200), rand.nextInt(100), 32,32));
        
    }
}
