package Game;

import Display.Window;
<<<<<<< HEAD
import GraphicHandler.Assets;
=======
import GraphicHandler.ImageLoader;
import GraphicHandler.Input;
import Objects.*;
import Objects.Button;

>>>>>>> 36b636895c4786954dd5105dd426b5b376364bf6
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Niki on 5.6.2016 г..
 */
public class Game extends Canvas implements Runnable {
    public static final int SCALE = 2;
    public static final int WIDTH = 512 * SCALE;
    public static final int HEIGHT = WIDTH / 12*9;
    public static final String TITLE = "Icy Somethink";

    private Menu menu;

    public boolean running = false;
    private Thread thread;

    public enum STATE {
        Menu,
        Game,
        Credentials,
        End;
    }

    public static STATE gameState = STATE.Menu;

    public static enum STATE{
        MENU,
        PLAY
    };
    public static STATE state = STATE.MENU;
    private Menu menu;

    private Button startButton;
    private Button exitButton;

    public Game(){
<<<<<<< HEAD
        Assets.init();

        menu = new Menu(this);
        this.addMouseListener(menu);

=======
>>>>>>> 36b636895c4786954dd5105dd426b5b376364bf6
        new Window(WIDTH, HEIGHT, TITLE, this);
    }

    public synchronized void start(){
        if (running){
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if (!running){
            return;
        }
        running = false;
        try{
            thread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.exit(1);
    }

    public void run() {
        init();
        while (running){
            this.requestFocus();
            long lastTime = System.nanoTime();
            double amountOfTicks = 60.0;
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

    private void init() {

        menu = new Menu();
        this.addMouseListener(new Input());


        startButton = new Button(WIDTH/2-100,150,imageLoader.loadImage("/res/StartButton.png"));
        exitButton = new Button(WIDTH/2-100,250,imageLoader.loadImage("/res/ExitButton.png"));
    }

    private void tick(){
<<<<<<< HEAD
        if (gameState == STATE.Menu){
            menu.tick();
        }
=======
        if (state == STATE.PLAY){
            //TODO
            System.out.println("PLAY");
        }

>>>>>>> 36b636895c4786954dd5105dd426b5b376364bf6
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

<<<<<<< HEAD
        if (gameState == Game.STATE.Menu) {
            menu.render(g);
        } else if (gameState == STATE.Game){
            g.fillRect(0, 0, WIDTH, HEIGHT);
        }
=======
        //g.setColor(Color.GRAY);
        //g.fillRect(0,0, WIDTH, HEIGHT);
        if (state == STATE.PLAY){
            //TODO
            System.out.println("play");

        }else if (state == STATE.MENU){
            menu.render(g);


        }


        startButton.render(g);
        exitButton.render(g);
>>>>>>> 36b636895c4786954dd5105dd426b5b376364bf6

        g.dispose();
        bs.show();
    }
}
