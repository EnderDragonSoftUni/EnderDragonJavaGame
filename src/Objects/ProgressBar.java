package Objects;

import Game.Game;

import java.awt.Color;
import java.awt.Graphics;

public class ProgressBar {

    private int x = 360;
    private int y = 25;
    private final int WIDTH = 260;
    private final int HEIGHT = 30;
    private int fillProgressBar = 0;
    private boolean progress = false;
    private Game game;


    public ProgressBar(Game game) {

        this.game = game;

    }

    public boolean isProgress() {
        return progress;
    }

    public void setProgress(boolean progress) {
        this.progress = progress;
    }

    public int getFillProgressBar() {
        return fillProgressBar;
    }

    public void setFillProgressBar(int fillProgressBar) {
        this.fillProgressBar = fillProgressBar;
    }


    public void tick() {

        if (progress) {
            fillProgressBar += 26;
        }
        if (fillProgressBar >= WIDTH) {
            Player.isDead = true;
        }
        progress = false;

    }

    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x + 3, y + 3, WIDTH, HEIGHT);
        g.setColor(Color.GRAY);
        g.fillRect(x, y, WIDTH, HEIGHT);
        g.setColor(Color.RED);
        g.fillRect(x, y, 0 + fillProgressBar, HEIGHT);
    }
}