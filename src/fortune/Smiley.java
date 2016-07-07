/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fortune;

import javax.swing.*;
import java.awt.*;

public class Smiley implements IRenderToCanvas {

    protected Image imageSrc;
    protected int posX;
    protected int posY;
    protected int speed = 10;
    protected int directionX = 0;
    protected int directionY = 0;
    protected int width = 40;
    protected int height = 40;
    protected int posRenderX;
    protected int posRenderY;
    protected MapPath mapPath;

    public Smiley() {
        String name = "../data/smiley.png";
        imageSrc = new ImageIcon(getClass().getResource(name)).getImage();
    }


    @Override
    public void render(Graphics g) {
        //перед рисованием вызываем метод обновления
        update();
        //используем статическое позиционирование
        g.drawImage(imageSrc, posRenderX, posRenderY, width, height, null);
    }

    /**
     * Метод обновляет данные о положении игрока на карте.
     */
    protected void update() {
        if (mapPath != null && mapPath.hasNext()) {
            if (mapPath.p == null) {
                mapPath.startMove(posX, posY);
            } else {
                mapPath.nextPos(speed);
                if (posX != mapPath.p.x) {
                    directionX = (posX > mapPath.p.x) ? -1 : 1;
                } else {
                    directionX = 0;
                }
                if (posY != mapPath.p.y) {
                    directionY = (posY > mapPath.p.y) ? -1 : 1;
                } else {
                    directionY = 0;
                }
            }
        } else {
            directionX = 0;
            directionY = 0;
        }

    }
}
