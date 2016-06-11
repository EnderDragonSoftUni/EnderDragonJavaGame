package Game;
import GraphicHandler.Assets;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
/**
 * Created by iliyapetrov on 11.06.16.
 */
public class Score {
    public static TreeMap<Long, String> highscores= new TreeMap<>(Collections.reverseOrder());
    private long score;
    private int highScoreX = Game.WIDTH / 2 - 180;
    private int highScoreY = 140;
    private int textX = highScoreX+55;
    private int textY=highScoreY+50;
    private final int WIDTH = 370;
    private final int HEIGHT = 150;
    private Font font = new Font("Serif", Font.BOLD, 20);
    public static ArrayList<String> topList = new ArrayList<>();

    public Score(long score) {
        this.score = score;
    }
    public long getScore() {
        return score;
    }
    public void setScore(long score) {
        this.score = score;
    }
    public void save(){
        long epoch = System.currentTimeMillis()/1000;
        String line;
        line = String.valueOf(score) + " " + String.valueOf(epoch) + "\n";
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
    public void getTop3()  {

        try (BufferedReader br = new BufferedReader(new FileReader(new File("./highscores.data")))){
            String line = null;
            while (  (line = br.readLine())!=null){

                String[] tokens = line.split(" ");
                highscores.put(Long.valueOf(tokens[0]), tokens[1]);
            }
            br.close();
            int counter= 0;
            for (long key:highscores.keySet()
                    ) {
                if (counter==3){
                    break;
                }
               String temp = (key+"..........."+EpochToDate(Long.valueOf(highscores.get(key))));
                topList.add(temp);

                counter++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String EpochToDate(long epoch){
        Date date = new Date(epoch*1000);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String str = df.format(date);
        return str;
    }
    public void render(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(this.highScoreX, this.highScoreY, this.WIDTH, this.HEIGHT);
        g.setColor(Color.BLUE);
g.setFont(font);
        g.drawString("HI-SCORE:", textX+55, textY);
        g.setColor(Color.yellow);
        g.drawString(topList.get(0), textX, textY+30);
        g.drawString(topList.get(1), textX, textY+55);
        g.drawString(topList.get(2), textX, textY+80);

        //for (int tempScore=0, y=textY; tempScore<topList.size(); tempScore++, y++){
          //  g.setFont(font);
           // g.drawString(topList.get(tempScore), textX, y);
        //}



    }
}
