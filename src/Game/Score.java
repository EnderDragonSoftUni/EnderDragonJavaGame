package Game;

import Display.NameBox;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class Score {
    public static Map<String, Long> highscores = new HashMap<>();
    private long score;
    private int highScoreX = Game.WIDTH / 2 - 120;
    private int highScoreY = 50;
    private int textX = 20;
    private int textY = highScoreY + 50;
    private final int WIDTH = 370;
    private final int HEIGHT = 150;
    private Font font = new Font("Showcard Gothic", Font.BOLD, 40);
    private Font font1 = new Font(Font.MONOSPACED, Font.BOLD, 25);
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
                String temp = entry.getKey() + " " + entry.getValue();
                topList.add(temp);
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void tick(Score score){

    score.save();
    score.getTop3();
}

    public void render(Graphics g) {
        this.getTop3();

        g.setColor(Color.BLUE);
        g.setFont(font);
        g.drawString("HIGH SCORES", highScoreX, highScoreY);

        g.setFont(font1);
        g.setColor(Color.BLACK);

        int poss = 0;
        for (int i = 0; i < topList.size() && i < 8; i++) {
            String[] data = topList.get(i).split("\\s+");
            String name = data[0];
            Long result = Long.parseLong(data[1]);

            g.drawString(String.format("%d.  %s%s%d", i + 1, name ,
                    fillWithSpaces(name, result), result) , textX, textY + poss);
            poss += 35;
        }
    }

    private String  fillWithSpaces(String name, Long result){
        StringBuilder spacesString = new StringBuilder();
        int spacesLenght = 35 - name.length() - String.valueOf(result).length();

        for (int i = 0; i < spacesLenght; i++) {
            spacesString.append(" ");
        }
        return spacesString.toString();
    }
}
