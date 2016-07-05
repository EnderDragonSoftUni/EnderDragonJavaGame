package Objects;

import Objects.gift.Gift;
import Game.Game;
import GraphicHandler.*;

import java.awt.*;
import java.util.Random;

public class Player {

    ;
    
    private int x, y, cropWidth, cropHeight, velocity, playerWidth, playerHeight;
    private int spriteCol;
    private int spriteRow;

    private PlatformHandler platformHandler;
    private GiftHandler giftHandler;
    private ProgressBar progressBar;
    private Random rand;

    private static SpriteSheet img = Assets.player;
    public static boolean inAir = true;
    public static boolean inJumpingBox = false;
    public static boolean isDead = false;
    public static double gravity = 0;
    public static boolean isMovingLeft, isMovingRight;
    public static final int THISVELOCITY = 15;
    public static final int CROPWIDTH = 108;
    public static final int CROPHEIGHT = 140;
    private static int bonusPoint = 0;

    public static final int VELOCITY = 15;

    public Player(int x, int y, int imgWidth, int imgHeight, PlatformHandler platformHandler, GiftHandler giftHandler, ProgressBar progressBar) {
        this.x = x;
        this.y = y;
        this.playerWidth = imgWidth;
        this.playerHeight = imgHeight;

        this.velocity = THISVELOCITY;
        this.cropWidth = CROPWIDTH;
        this.cropHeight = CROPHEIGHT;
        this.platformHandler = platformHandler;
        this.giftHandler = giftHandler;
        this.progressBar = progressBar;
        this.rand = new Random();
        this.spriteCol = 0;
        this.spriteRow = 0;

    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.playerWidth, this.playerHeight);
    }

    public Rectangle getBotBounds() {
        return new Rectangle(this.x, this.y + this.playerHeight * 3 / 4, this.playerWidth, this.playerHeight / 4);
    }

    public void tick() {
        if (getBounds().intersects(new Rectangle(0, 475, 700, 60))) {
            isDead = true;
        }

        if (x < this.cropWidth * -1) {
            x = Game.WIDTH;
        } else if (x > Game.WIDTH) {
            x = this.cropWidth * -1;
        }

        if (hasCollision() && !InputHandler.jumped) {
            y += 3;
            gravity = 0;
            inAir = false;
        } else {
            inAir = true;
        }

        if (giftsCollision()) {
            // Sound.playSound("res/audio/WOW.wav");

            Game.score += bonusPoint;//100 * this.y / 250;
            progressBar.setProgress(true);
        }

        InputHandler.jumped = false;

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
        this.y += gravity;
        if (inAir) {
            gravity += 2.2;
        }
    }

    public void render(Graphics g) {
        g.drawImage(img.crop(this.spriteCol * this.cropWidth, this.spriteRow * this.cropHeight, this.cropWidth, this.cropHeight), this.x, this.y, this.playerWidth, this.playerHeight, null);
//        g.setColor(Color.YELLOW);
//        g.drawRect(getBotBounds().x,getBotBounds().y,getBotBounds().width,getBotBounds().height);

    }

    private boolean hasCollision() {
        boolean collis = false;
        inJumpingBox = false;
        for (int i = 0; i < PlatformHandler.objects.size(); i++) {
            Platform tempObject = PlatformHandler.objects.get(i);

            if (this.getBotBounds().intersects(tempObject.getTopBounds())) {
                collis = true;
                y = tempObject.getTopBounds().y - this.playerHeight + 13;
            }
            if (this.getBotBounds().intersects(tempObject.getJumpingBounds())) {
                inJumpingBox = true;
            }
        }
        return collis;
    }

    private boolean giftsCollision() {
        boolean giftsCollision = false;
        for (int i = 0; i < GiftHandler.objects.size(); i++) {
            Gift tempObject = GiftHandler.objects.get(i);

            if (this.getBounds().intersects(tempObject.getBounds())) {
                giftsCollision = true;
                getBonusPoint(GiftHandler.objects.get(i));
                GiftHandler.objects.remove(i);
                //y = tempObject.getTopBounds().y - this.playerHeight + 13;
            }

        }
        return giftsCollision;
    }

    private void getBonusPoint(Gift get) {
        switch (get.getClass().getSimpleName()) {
            case "GoldCoin":
                bonusPoint = 500;
                break;
            case "SilverCoin":
                bonusPoint = 200;
                break;
            case "CopperCoin":
                bonusPoint = 75;
                break;
            default:
                bonusPoint = 0;

        }

    }
}
