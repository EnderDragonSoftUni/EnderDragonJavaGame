package Game;

import Display.Window;
import GraphicHandler.Assets;
import Objects.Button;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by Niki on 5.6.2016 г..
 */
public class Game extends Canvas implements Runnable {
    public static final int SCALE = 2;
    public static final int WIDTH = 320 * SCALE;
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

    private Button startButton;
    private Button exitButton;

    public Game(){
        Assets.init();

        menu = new Menu(this);
        this.addMouseListener(menu);

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

    private void tick(){

        if (gameState == STATE.Menu){
            menu.tick();
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();


        if (gameState == Game.STATE.Menu) {
            menu.render(g);
        } else if (gameState == STATE.Game){
            g.drawImage(Assets.background, 0, 0, this);
        }

        g.dispose();
        bs.show();
    }
}
