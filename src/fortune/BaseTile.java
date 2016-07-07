/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fortune;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;


/**
 * Базовый элемент отображения плитки карты.
 */
public class BaseTile implements IRenderToCanvas {

    /**
     * Размер плитки - ширина, высота.
     */
    final public static int SIZE = 40;

    /**
     * Буфер загруженных изображений.
     */
    private static HashMap<String, Image> images = new HashMap<String, Image>();

    /**
     * Имя изображения.
     */
    public String image;
    /**
     * положение плитки по оси  X
     */
    public int posX;
    /**
     * положение плитки по оси  Y
     */
    public int posY;

    /**
     * Можно пройти
     */
    public boolean isWalkable;

    /**
     * Идентификатор двери. По умолчанию нет.
     */
    public int door = 0;

    public BaseTile(String image, int posX, int posY, boolean isWalkable) {
        this.image = image;
        this.posX = posX;
        this.posY = posY;
        this.isWalkable = isWalkable;
    }

    public BaseTile(String image, int posX, int posY, boolean isWalkable, int door) {
        this.image = image;
        this.posX = posX;
        this.posY = posY;
        this.isWalkable = isWalkable;
        this.door = door;
    }

    /**
     * Получить плитку по номеру (ИД).
     *
     * @param tileId
     * @return BaseTile
     */
    public static BaseTile getTileById(int tileId) {
        if (tileId == 1) {
            return new BaseTile("../data/white.png", 0, 0, false);
        }
        if (tileId == 2) {
            return new BaseTile("../data/black.png", 0, 0, true);
        }
        if (tileId == 3) {
            return new BaseTile("../data/red.png", 0, 0, true, 2);
        }
        if (tileId == 4) {
            return new BaseTile("../data/red.png", 0, 0, true, 1);
        }
        if (tileId == 5) {
            return new BaseTile("../data/red.png", 0, 0, true, 3);
        }
        if (tileId == 6) {
            return new BaseTile("../data/rock2.png", 0, 0, false);
        }
        return null;

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getImage(image), posX, posY, SIZE, SIZE, null);
    }

    /**
     * Вернет изображение по ссылке.
     *
     * @param name
     * @return
     */
    public static Image getImage(String name) {
        if (images.get(name) == null) {
            images.put(name, new ImageIcon(BaseTile.class.getResource(name)).getImage());
        }
        return images.get(name);
    }
}
