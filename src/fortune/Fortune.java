package fortune;

import game.Game;

import javax.swing.*;

/**
 * Created by oxana_bs on 6.7.2016 г..
 */
public class Fortune {
    private LabyrinthEngine labyrinthEngine;

    public Fortune() {
        this.labyrinthEngine = new LabyrinthEngine();
    }

    public void start() {
        this.labyrinthEngine.start();
        JFrame f = new JFrame();
        f.setLocation(Game.WIDTH, Game.HEIGHT);
        f.requestFocus();
        labyrinthEngine.getLabyrinth().setBounds(0, 0, 680, 720);
        f.add(labyrinthEngine.getLabyrinth());
        f.setTitle("Fortune");
        f.setBounds(300, 120, 680, 720);
        f.setVisible(true);
    }
}
