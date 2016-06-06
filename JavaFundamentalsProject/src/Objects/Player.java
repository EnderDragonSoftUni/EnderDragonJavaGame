package Objects;

import Game.Game;
import GraphicHandler.Assets;
import GraphicHandler.SpriteSheet;

import java.awt.*;

public class Player {
    private int x, y, width, height, velocity;
    private int column = 0;
    private int row = 0;

    private SpriteSheet img;
    private Rectangle boundingBox;

    public static boolean inAir = true;
    public static double gravity = 0;
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

        if (x < this.width*-1){
            x = Game.WIDTH;
        }else if(x > Game.WIDTH){
            x = this.width*-1;
        }


        if (boundingBox.intersects(BigPlatform.boundingBox) && gravity > 0){
            gravity=0;
            inAir = false;
        }else{
            this.y += this.gravity;
            inAir = true;
        }

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

        if (this.gravity < 14){
            this.gravity+=0.3;
        }

        this.boundingBox.setBounds(this.x, this.y, this.width, this.height);
    }

    public void render(Graphics g) {
        g.drawImage(this.img.crop(this.column * this.width, this.row * this.height, this.width, this.height)
                , this.x, this.y, null);

    }
}
