package raceclient.display;

import raceclient.Race;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class RaceWindow extends JFrame implements MouseListener {

    public boolean loggedIn = false;
    public boolean validIP = false;

    private Race race;
    private BufferedImage image;
    public IPInput ipInput;
    public RaceWindow(String title, Race race){
        super(title);
        this.race = race;
        ipInput = new IPInput();
        Graphics g = this.getGraphics();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
        image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        add(ipInput);
        setLayout(null);
    }

    public void update(Graphics g){
        paint(g);
    }

    public void paint(Graphics g){
        updateImage();
        g.drawImage(image, 0, 0, null);
    }

    private void updateImage(){
        Graphics2D g2d = image.createGraphics();
        if(loggedIn) {
            g2d.setColor(Color.GREEN);
            g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
        }else{
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
        }
        g2d.dispose();
    }

    public void ping(){
        validIP = true;
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
