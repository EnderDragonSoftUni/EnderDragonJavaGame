package Game;

import Display.NameBox;
import Display.Window;
import GraphicHandler.*;
import Objects.HighScore;
import Objects.Player;
import Objects.ProgressBar;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

/**
 * Created by Niki on 5.6.2016 г..
 */
public class Game extends Canvas implements Runnable {

    public static final int SCALE = 2;
    public static final int WIDTH = 320 * SCALE;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final String TITLE = "Icy Tower+";
    public static int score = 0;
    public static Score currentScore;

    public static boolean item1unlocked = false;
    public static boolean item2unlocked = false;
    public static boolean item3unlocked = false;

    private Menu menu;
    private HighScore highScore;
    public boolean running = false;
    private Thread thread;
    private Player player;
    private PlatformHandler platformHandler;
    private GiftHandler giftHandler;
    private ProgressBar progressBar;
    private LevelHandler levelHandler;
    private InputHandler inputHandler;


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public enum STATE {
        Menu,
        Game,
        Shop,
        HighScore,
        Credentials,
        End
    }

    public static STATE gameState = STATE.Menu;

    public Game() throws IOException {
        Assets.init();
        this.platformHandler = new PlatformHandler();
        this.giftHandler = new GiftHandler();
        this.highScore = new HighScore(score);
        this.currentScore = new Score(score);
        this.progressBar = new ProgressBar(this);
        this.levelHandler = new LevelHandler(this.platformHandler, this.giftHandler);

        PlatformHandler.addStartingPlatforms();
        GiftHandler.addRandomGifts();
        this.createPlayer();

        menu = new Menu(this, platformHandler);
        this.addMouseListener(menu);
        this.inputHandler = new InputHandler(this);

        new Window(WIDTH, HEIGHT, TITLE, this);
        new NameBox();
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
                    Thread.sleep(1);
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
            levelHandler.tick();

            if (Player.isDead) {
                this.resetGame();
            }
        } else if (gameState == STATE.Menu) {
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

        if (gameState == STATE.Game || gameState == STATE.End) {
            levelHandler.render(g);
        } else {
            g.drawImage(Assets.background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
        }

        if (gameState == STATE.Game) {
            player.render(g);
            platformHandler.render(g);
            giftHandler.render(g);
            highScore.render(g);
            progressBar.render(g);

        } else if (gameState == Game.STATE.Menu ||
                gameState == STATE.End ||
                gameState == STATE.Shop ||
                gameState == STATE.HighScore) {
            menu.render(g);
        } else if (gameState == STATE.HighScore) {
        }

        g.dispose();
        bs.show();
    }

    public void resetGame() {
        currentScore = new Score(score);
        Score.tick(currentScore);
        Game.gameState = Game.STATE.End;
        Player.isDead = false;
        PlatformHandler.clearAllPlatforms();
        PlatformHandler.addStartingPlatforms();
        GiftHandler.clearAllGifts();
        GiftHandler.addRandomGifts();
        progressBar.setFillProgressBar(0);
        LevelHandler.setCurrentLevel(1);
        InputHandler.beginning = true;
        LevelHandler.levelPassed();
    }

    public void createPlayer() {
        player = new Player(WIDTH / 2 - 60, 345, 60, 70, platformHandler, giftHandler, progressBar);
    }
}