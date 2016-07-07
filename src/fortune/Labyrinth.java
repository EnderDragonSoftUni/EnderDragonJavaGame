package fortune;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Labyrinth extends JPanel {

    protected Image bufer = null;
    protected Color backGround = Color.black;
    protected ArrayList<IRenderToCanvas> renders = new ArrayList<IRenderToCanvas>();

    public void addRender(IRenderToCanvas render) {
        renders.add(render);
    }
    public void removeRenders() {
        renders.clear();
    }

    public void paintLabyrinth(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        g.setColor(backGround);
        g.fillRect(0, 0, getWidth(), getHeight());
        try {
            for (int i = 0; i < renders.size(); i++) {
                IRenderToCanvas render = renders.get(i);

                render.render(g);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (bufer == null) {
            bufer = createImage(getWidth(), getHeight());
        }
        paintLabyrinth(bufer.getGraphics());
        g.drawImage(bufer, 0, 0, null);
    }
}
