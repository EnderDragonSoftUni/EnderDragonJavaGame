package Objects;

import java.awt.*;

/**
 * Created by Rosen on 04-Jun-16.
 */
public class Platform {
    private int x;
    private int y;
    private int width;
    private int height;

    public Platform(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        Color myColour = new Color(0, 0, 0, 127);
        g.setColor(myColour);
        g.fillRect(x, y, this.width, this.height);
    }
}
