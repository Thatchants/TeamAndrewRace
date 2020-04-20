package raceclient.display;

import raceclient.Race;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RaceWindow extends JFrame implements MouseListener {

    public boolean loggedIn = false;

    private Race race;
    public IPInput ipInput;
    public CustomGraphics graphics;
    public RaceWindow(String title, Race race){
        super(title);
        this.race = race;
        setSize(500, 500);
        ipInput = new IPInput(this);
        graphics = new CustomGraphics(this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        JLayeredPane layers = new JLayeredPane();
        layers.setSize(getWidth(), getHeight());
        layers.add(graphics, JLayeredPane.DEFAULT_LAYER);
        layers.add(ipInput, JLayeredPane.POPUP_LAYER);
        setContentPane(layers);
        setLayout(null);
    }



    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
