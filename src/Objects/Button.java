package Objects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button {

    private int x;
    private int y;
    private BufferedImage image;

    public Button(int x, int y, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(image, x, y, 200, 80, null);
    }
}
