package Game;

import Display.Window;
import GraphicHandler.Assets;
import GraphicHandler.InputHandler;
import GraphicHandler.PlatformHandler;
import Objects.Player;
import GraphicHandler.GiftHandler;


import java.awt.*;
import java.awt.image.BufferStrategy;

import Objects.HighScore;
import Objects.ProgressBar;

/**
 * Created by Niki on 5.6.2016 г..
 */
public class Game extends Canvas implements Runnable {
    public static final int SCALE = 2;
    public static final int WIDTH = 320 * SCALE;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final String TITLE = "Icy Somethink";
    public static int score = 0;


    private Menu menu;
    private HighScore highScore;
    public boolean running = false;
    private Thread thread;
    private Player player;
    private PlatformHandler platformHandler;
    private GiftHandler giftHandler;
    private ProgressBar progressBar;


    private InputHandler inputHandler;


    public int getScore() {
        return score;
    }

    public void setScore(int x) {
        this.score = score;
    }

    public enum STATE {
        Menu,
        Game,
        Credentials,
        End
    }

    public static STATE gameState = STATE.Menu;

    public Game() {
        Assets.init();
        platformHandler = new PlatformHandler();
        giftHandler = new GiftHandler();
        highScore = new HighScore(score);
        progressBar = new ProgressBar(this);

        platformHandler.addStartingPlatforms();
        giftHandler.addStartingGifts();
        player = new Player(WIDTH / 2 - 60, 345, 60, 70, platformHandler, giftHandler, progressBar);

        menu = new Menu(this, platformHandler);
        this.addMouseListener(menu);
        this.inputHandler = new InputHandler(this);

        new Window(WIDTH, HEIGHT, TITLE, this);
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    public void run() {
        while (running) {
            this.requestFocus();
            long lastTime = System.nanoTime();
            double amountOfTicks = 20.0;
            double ns = 1000000000 / amountOfTicks;
            double delta = 0;
            long timer = System.currentTimeMillis();
            int frames = 0;
            while (running) {

                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;

                try {
                    thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                while (delta >= 1) {
                    tick();
                    delta--;
                }
                if (running) {
                    render();
                }
                frames++;
                if (System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;
                    System.out.println("FPS " + frames);
                    frames = 0;
                }
            }
            stop();
        }
    }

    private void tick() {
        if (gameState == STATE.Game) {
            player.tick();
            platformHandler.tick();
            giftHandler.tick();
            progressBar.tick();
            highScore.tick(score);
            if (Player.isDead) {
                Game.gameState = Game.STATE.End;
                Player.isDead = false;
                platformHandler.clearAllPlatforms();
                platformHandler.addStartingPlatforms();
                giftHandler.clearAllGifts();
                giftHandler.addStartingGifts();
                progressBar.setFillProgressBar(0);

                player = new Player(WIDTH / 2 - 60, 345, 60, 70, platformHandler, giftHandler, progressBar);
                InputHandler.beginning = true;
            }
        } else if (gameState == STATE.Menu || gameState == STATE.End) {
            menu.tick();
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.drawImage(Assets.background, 0, 0, WIDTH, HEIGHT, null);

        if (gameState == STATE.Game) {
            player.render(g);
            platformHandler.render(g);
            giftHandler.render(g);
            highScore.render(g);
            progressBar.render(g);


        } else if (gameState == Game.STATE.Menu || gameState == STATE.End) {
            this.score = 0;
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }
}
