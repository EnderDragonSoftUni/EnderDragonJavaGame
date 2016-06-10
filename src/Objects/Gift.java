/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import Game.Game;
import GraphicHandler.Assets;
import GraphicHandler.InputHandler;
import GraphicHandler.SpriteSheet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import GraphicHandler.GiftHandler;

public class Gift {

    private int x;
    private int y;
    private int width;
    private int height;
    private SpriteSheet img;
    private Game game;
    private float frame = 0;
   

    public Gift(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = Assets.giftsImg;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }


    public void tick() {
        int tester = (int)(frame + .2);
        if (tester < 4){
            frame += .2;
        }else {
            frame = 0;
        }
        if (InputHandler.beginning == false) {
            y += 6;
        }
        if (this.y > Game.HEIGHT) {
            GiftHandler.removeObject(this);
        }
        
        
    }

    public void render(Graphics g) {
        Color myColour = new Color(0, 0, 0, 0);
        g.setColor(myColour);
        g.fillRect(x,y,this.width,this.height);
        g.drawImage(  this.img.crop( 32* (int)frame, 32* (int)frame, 32, 32), this.x , this.y ,  32, 32  ,null);

    }

}
