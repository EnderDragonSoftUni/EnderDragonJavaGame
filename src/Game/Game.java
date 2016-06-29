package Game;

import Display.NameBox;
import Display.Window;
import GraphicHandler.*;
import Objects.HighScore;
import Objects.Player;
import Objects.ProgressBar;

import java.awt.*;
import java.awt.image.BufferStrategy;

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
        this.platformHandler = new PlatformHandler();
        this.giftHandler = new GiftHandler();
        this.highScore = new HighScore(score);
        this.progressBar = new ProgressBar(this);
        this.levelHandler = new LevelHandler(this.platformHandler, this.giftHandler);

        platformHandler.addStartingPlatforms();
        giftHandler.addStartingGifts();
        player = new Player(WIDTH / 2 - 60, 345, 60, 70, platformHandler, giftHandler, progressBar);

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
                currentScore = new Score(score);
                //currentScore.save();
                //currentScore.getTop3();
                Score.tick(currentScore);
                Game.gameState = Game.STATE.End;
                Player.isDead = false;
                PlatformHandler.clearAllPlatforms();
                PlatformHandler.addStartingPlatforms();
                GiftHandler.clearAllGifts();
                GiftHandler.addStartingGifts();
                progressBar.setFillProgressBar(0);
                LevelHandler.setCurrentLevel(1);

                player = new Player(WIDTH / 2 - 60, 345, 60, 70, platformHandler, giftHandler, progressBar);
                InputHandler.beginning = true;
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


        } else if (gameState == Game.STATE.Menu) {
            this.score = 0;
            menu.render(g);
        } else if (gameState == STATE.End) {
            this.score = 0;
            menu.render(g);
            currentScore.render(g);
        }

        g.dispose();
        bs.show();
    }
}
