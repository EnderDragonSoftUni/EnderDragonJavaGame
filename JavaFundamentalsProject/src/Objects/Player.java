package Objects;

import Game.Game;
import GraphicHandler.Assets;
import GraphicHandler.SpriteSheet;

import java.awt.*;

/**
 * Created by Rosen on 04-Jun-16.
 */
public class Player {
    private int x, y, width, height, velocity;
    private SpriteSheet img;
    private Rectangle boundingBox;

    private int column = 0;
    private int row = 0;

    public static boolean isMovingLeft, isMovingRight;


    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.velocity = 15;
        this.width = 108;
        this.height = 140;
        this.img = Assets.player;
        this.boundingBox = new Rectangle(x, y, this.width, this.height);
    }

    public void tick() {
        if (isMovingRight) {
            this.x += this.velocity;
            this.row = 0;
            this.column++;
            this.column %= 8;
        } else if (isMovingLeft) {
            this.x -= this.velocity;
            this.row = 1;
            this.column++;
            this.column %= 8;
        } else {
            this.column = 0;
        }

        this.boundingBox.setBounds(this.x, this.y, this.width, this.height);
    }

    public void render(Graphics g) {
        g.drawImage(this.img.crop(this.column * this.width, this.row * this.height, this.width, this.height)
                , this.x, Game.HEIGHT / 2, null);

    }
}
