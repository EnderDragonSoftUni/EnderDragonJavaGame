package Objects;

import Game.Game;
import GraphicHandler.Assets;
import GraphicHandler.InputHandler;
import GraphicHandler.PlatformHandler;
import GraphicHandler.SpriteSheet;

import java.awt.*;

public class Player {
    private int x, y, cropWidth, cropHeight, velocity, playerWidth, playerHeight;
    private int spriteCol = 0;
    private int spriteRow = 0;

    private SpriteSheet img;
    private PlatformHandler platformHandler;

    public static boolean inAir = true;
    public static boolean inJumpingBox = false;
    public static double gravity = 0;
    public static boolean isMovingLeft, isMovingRight;


    public Player(int x, int y, int imgWidth, int imgHeight, PlatformHandler platformHandler) {
        this.x = x;
        this.y = y;
        this.playerWidth = imgWidth;
        this.playerHeight = imgHeight;
        this.velocity = 15;
        this.cropWidth = 108;
        this.cropHeight = 140;
        this.img = Assets.player;
        this.platformHandler = platformHandler;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.playerWidth, this.playerHeight);
    }
    public Rectangle getBotBounds() {return new Rectangle(this.x, this.y+this.playerHeight*3/4, this.playerWidth, this.playerHeight/4);}

    public void tick() {

        if (x < this.cropWidth * -1) {
            x = Game.WIDTH;
        } else if (x > Game.WIDTH) {
            x = this.cropWidth * -1;
        }

//        y+=gravity;

        if (collision() && !InputHandler.jumped) {
            y+=3;
            gravity = 0;
            inAir = false;
        } else {
            inAir = true;
        }

        this.y += this.gravity;
        InputHandler.jumped = false;

//        System.out.println(inAir);

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

        if (inAir){
            this.gravity += 2.2;
        }
    }

    public void render(Graphics g) {
        g.drawImage(this.img.crop(this.spriteCol * this.cropWidth, this.spriteRow * this.cropHeight, this.cropWidth, this.cropHeight)
                , this.x, this.y, this.playerWidth, this.playerHeight, null);
//        g.setColor(Color.YELLOW);
//        g.drawRect(getBotBounds().x,getBotBounds().y,getBotBounds().width,getBotBounds().height);

    }

    private boolean collision() {
        boolean collis = false;
        inJumpingBox = false;
        for (int i = 0; i < this.platformHandler.objects.size(); i++) {
            Platform tempObject = this.platformHandler.objects.get(i);

            if (this.getBotBounds().intersects(tempObject.getTopBounds())) {
                collis = true;
                y = tempObject.getTopBounds().y-this.playerHeight+8;
            }
            if (this.getBotBounds().intersects(tempObject.getJumpingBounds())){
                inJumpingBox = true;
            }
        }

        return collis;
    }
}
