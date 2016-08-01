package display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameBox extends JFrame {

    private final JFrame frame = new JFrame();
    public static String playerName = "NoName";
    private Window window;

    public NameBox() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Please Enter Your Name");
        JTextField textField = new JTextField("", 30);
        JButton enterButt = new JButton("Enter");

        frame.setUndecorated(true);
        frame.setTitle("Enter you name");
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.requestFocus();
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Font fnt = new Font("Calibri", Font.BOLD, 20);
        label.setFont(fnt);

        panel.add(label, 0);
        panel.add(textField, 1);
        panel.add(enterButt, 2);
        frame.add(panel);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField.getText();
                setPlayerName(input);
                frame.dispose();
            }
        });

        enterButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField.getText();
                setPlayerName(input);
                frame.dispose();
            }
        });
    }

    private void setPlayerName(String name) {
        System.out.println(name);
        if (name.equals("") || name.contains(" ")) {
            playerName = "NoName";
        } else {
            playerName = name;
        }
    }
}