package raceclient.display;

import javax.swing.*;
import java.awt.*;
public class IPInput extends JTextField {
    private RaceWindow raceWindow;
    public IPInput(RaceWindow raceWindow){
        super("Enter Server IP");
        this.raceWindow = raceWindow;
        setBounds(0, 0, raceWindow.getWidth(), 60);
        setFont(new Font("Courier", Font.PLAIN, 40));
        setBackground(Color.RED);
    }

    public void setColor(Color color){
        setBackground(color);
    }
}
