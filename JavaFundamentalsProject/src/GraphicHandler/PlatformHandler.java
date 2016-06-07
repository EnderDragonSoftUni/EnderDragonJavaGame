package GraphicHandler;

import Objects.Platform;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Niki on 7.6.2016 г..
 */
public class PlatformHandler {
    public static  ArrayList<Platform> object = new ArrayList<>();

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            Platform tempObject = object.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        try {
            for (int i = 0; i < object.size(); i++) {
                Platform tempObject = object.get(i);

                tempObject.render(g);
            }
        } catch (Exception e) {

        }
    }

    public void addObject(Platform object) {
        this.object.add(object);
    }

    public void removeObject(Platform object) {
        this.object.remove(object);
    }
}
