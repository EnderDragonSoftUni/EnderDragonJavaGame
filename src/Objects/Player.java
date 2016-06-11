package Objects;

import Game.Game;
import GraphicHandler.Assets;
import GraphicHandler.InputHandler;
import GraphicHandler.PlatformHandler;
import GraphicHandler.SpriteSheet;
import GraphicHandler.GiftHandler;

import java.awt.*;

public class Player {
    private int x, y, cropWidth, cropHeight, velocity, playerWidth, playerHeight;
    private int spriteCol = 0;
    private int spriteRow = 0;

    private SpriteSheet img;
    private PlatformHandler platformHandler;
    private GiftHandler giftsHandler;
    private ProgressBar progressBar;

    public static boolean inAir = true;
    public static boolean inJumpingBox = false;
    public static boolean isDead = false;
    public static double gravity = 0;
    public static boolean isMovingLeft, isMovingRight;
    public static final int CROPWIDTH = 108;
    public static final int THISVELOCITY = 15;
    public static final int CROPHEIGHT = 140;



    public static final int VELOCITY = 15;
    public Player(int x, int y, int imgWidth, int imgHeight, PlatformHandler platformHandler, GiftHandler giftsHandler, ProgressBar progressBar) {
        this.x = x;
        this.y = y;
        this.playerWidth = imgWidth;
        this.playerHeight = imgHeight;


        this.velocity = THISVELOCITY;
        this.cropWidth = CROPWIDTH;
        this.cropHeight = CROPHEIGHT;
        this.img = Assets.player;
        this.platformHandler = platformHandler;
        this.giftsHandler = giftsHandler;
        this.progressBar = progressBar;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.playerWidth, this.playerHeight);
    }

    public Rectangle getBotBounds() {
        return new Rectangle(this.x, this.y + this.playerHeight * 3 / 4, this.playerWidth, this.playerHeight / 4);
    }

    public void tick() {
        if (getBounds().intersects(new Rectangle(0, 475, 700, 60))){
            isDead = true;
        }

        if (x < this.cropWidth * -1) {
            x = Game.WIDTH;
        } else if (x > Game.WIDTH) {
            x = this.cropWidth * -1;
        }

        if (collision() && !InputHandler.jumped) {
            y += 3;
            gravity = 0;
            inAir = false;
        } else {
            inAir = true;
        }
        
        if (giftsCollision() ) {
         
            Game.score +=100;
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
        this.y += this.gravity;
        if (inAir) {
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
        for (int i = 0; i < this.giftsHandler.objects.size(); i++) {
            Gift tempObject = this.giftsHandler.objects.get(i);

            if (this.getBotBounds().intersects(tempObject.getBounds())) {
                giftsCollision = true;
                this.giftsHandler.objects.remove(i);
                //y = tempObject.getTopBounds().y - this.playerHeight + 13;
            }
            
        }
        return giftsCollision;
    }
}
