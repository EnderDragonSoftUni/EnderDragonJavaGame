package Game;

import Display.NameBox;
import GraphicHandler.Assets;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * Created by iliyapetrov on 11.06.16.
 */
public class Score {
    public static Map<String, Long> highscores = new HashMap<>();
    private long score;
    private int highScoreX = Game.WIDTH / 2 - 180;
    private int highScoreY = 140;
    private int textX = highScoreX + 55;
    private int textY = highScoreY + 50;
    private final int WIDTH = 370;
    private final int HEIGHT = 150;
    private Font font = new Font("Serif", Font.BOLD, 20);
    public static List<String> topList = new ArrayList<>();

    public Score(long score) {
        this.score = score;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public void save() {
        long epoch = System.currentTimeMillis() / 1000;
        String line;
        line = NameBox.playerName + " " + String.valueOf(score) + "\n";

        byte data[] = line.getBytes();
        Path p = Paths.get("./highscores.data");
        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(p, CREATE, APPEND))) {
            out.write(data, 0, data.length);
            out.close();
        } catch (IOException x) {
            System.err.println(x);
        }
    }

    public void getTop3() {

        try (BufferedReader br = new BufferedReader(new FileReader(new File("./highscores.data")))) {
            topList = new ArrayList<>();
            String line = null;
            while ((line = br.readLine()) != null) {

                String[] tokens = line.split(" ");
                if (!highscores.containsKey(tokens[0]) || highscores.get(tokens[0]) < Long.valueOf(tokens[1])) {
                    highscores.put(tokens[0], Long.valueOf(tokens[1]));
                }
            }
            br.close();

            highscores.entrySet().stream().sorted((s1, s2) -> s2.getValue().compareTo(s1.getValue())).forEach(entry -> {
                String temp = entry.getKey() + "............" + entry.getValue();
                topList.add(temp);
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(this.highScoreX, this.highScoreY, this.WIDTH, this.HEIGHT);
        g.setColor(Color.BLUE);
        g.setFont(font);
        g.drawString("HIGH SCORE:", textX + 55, textY);
        g.setColor(Color.yellow);


        int poss = 30;
        for (int i = 0; i < topList.size() && i < 3; i++) {
            g.drawString(topList.get(i), textX, textY + poss);
            poss += 25;
        }
    }
}
