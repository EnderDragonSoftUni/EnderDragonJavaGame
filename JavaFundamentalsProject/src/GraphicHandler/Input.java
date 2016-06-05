package GraphicHandler;

import Game.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by kalin on 05.06.16.
 */
public class Input implements ActionListener , MouseListener {


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        //PLAY
        if (mouseX >=Game.WIDTH/2-100 && mouseX <= Game.WIDTH/2+100){
            if (mouseY >=150 && mouseY <= 200){

                //Pressed PLAY
                Game.state = Game.STATE.PLAY;
            }


        }

        //EXIT
        if (mouseX >=Game.WIDTH/2-100 && mouseX <= Game.WIDTH/2+100){
            if (mouseY >=250 && mouseY <= 300){

                //Pressed PLAY
                System.exit(0);
            }


        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);

    }
}
