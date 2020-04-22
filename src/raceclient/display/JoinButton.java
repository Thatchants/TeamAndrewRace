package raceclient.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JoinButton extends JPanel implements MouseListener {
    private Color color;
    private RaceWindow raceWindow;

    public JoinButton(RaceWindow raceWindow, int x, int y, int width, int height, Color color) {
        this.color = color;
        this.raceWindow = raceWindow;
        setBounds(x, y, width, height);
        addMouseListener(this);
    }

    public void paint(Graphics g){
        g.setColor(color);
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Courier", Font.PLAIN, 15));
        CustomGraphics.drawCenteredString(g,"Join Server",new Rectangle(0,0,getWidth(),getHeight()),g.getFont());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.raceWindow.ipInput.getBackground().equals(Color.GREEN)) {
            raceWindow.race.username = raceWindow.usernameInput.getText();
            raceWindow.race.tryLogin(raceWindow.race.username);
        }
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
