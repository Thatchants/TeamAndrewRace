package raceclient.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class IPInput extends JTextField implements MouseListener {
    private RaceWindow raceWindow;
    public IPInput(RaceWindow raceWindow,int x,int y,int width,int height){
        super("Enter Server IP");
        this.raceWindow = raceWindow;
        setBounds(x, y, width, height);
        setFont(new Font("Courier", Font.PLAIN, 40));
        setBackground(Color.RED);
        addMouseListener(this);
    }

    public void setColor(Color color){
        setBackground(color);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(getText().equals("Enter Server IP"))
        setText("");
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
}
