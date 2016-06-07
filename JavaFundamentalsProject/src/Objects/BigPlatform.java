package Objects;

import java.awt.*;

public class BigPlatform {
    private int x;
    private int y;
    private int width;
    private int height;
    public static Rectangle boundingBox;

    public BigPlatform(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 1000;
        this.height = 80;
        this.boundingBox = new Rectangle(x, y, this.width, this.height);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        Color myColour = new Color(0, 0, 0, 127);
        g.setColor(myColour);
        g.fillRect(x, y, this.width, this.height);
    }
}
