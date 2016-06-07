package Objects;

import Game.Game;
import GraphicHandler.Assets;
import GraphicHandler.SpriteSheet;

import java.awt.*;

public class Player {
    private int x, y, cropWidth, cropHeight, velocity, playerWidth, playerHeight;
    private int spriteCol = 0;
    private int spriteRow = 0;

    private SpriteSheet img;
    private Rectangle boundingBox;

    public static boolean inAir = true;
    public static double gravity = 0;
    public static boolean isMovingLeft, isMovingRight;


    public Player(int x, int y, int imgWidth, int imgHeight) {
        this.x = x;
        this.y = y;
        this.playerWidth = imgWidth;
        this.playerHeight = imgHeight;
        this.velocity = 15;
        this.cropWidth = 108;
        this.cropHeight = 140;
        this.img = Assets.player;
        this.boundingBox = new Rectangle(this.x, this.y, this.playerWidth, this.playerHeight);
    }

    public void tick() {

        if (x < this.cropWidth * -1) {
            x = Game.WIDTH;
        } else if (x > Game.WIDTH) {
            x = this.cropWidth * -1;
        }


        if (boundingBox.intersects(BigPlatform.boundingBox) && gravity > 0) {
            gravity = 0;
            inAir = false;
        } else {
            this.y += this.gravity;
            inAir = true;
        }

        if (isMovingRight) {
            this.x += this.velocity;

            this.spriteRow = 0;
            this.spriteCol++;
            this.spriteCol %= 8;
        } else if (isMovingLeft) {
            this.x -= this.velocity;

            this.spriteRow = 1;
            this.spriteCol++;
            this.spriteCol %= 8;
        } else {
            this.spriteCol = 0;
        }

        if (this.gravity < 14) {
            this.gravity += 0.5;
        }

        this.boundingBox.setBounds(this.x, this.y, this.playerWidth, this.playerHeight);
    }

    public void render(Graphics g) {
        g.drawImage(this.img.crop(this.spriteCol * this.cropWidth, this.spriteRow * this.cropHeight, this.cropWidth, this.cropHeight)
                , this.x, this.y, this.playerWidth, this.playerHeight, null);

    }
}
