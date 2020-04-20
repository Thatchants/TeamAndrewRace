package raceclient.display;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CustomGraphics extends JPanel {
    private BufferedImage image;
    private RaceWindow window;
    public CustomGraphics(RaceWindow window){
        this.window = window;
        setSize(window.getWidth(), window.getHeight());
        image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
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
        if(window.loggedIn) {
            //remove(getComponent(0));
            g2d.setColor(Color.GREEN);
            g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
        }else{
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
        }
        g2d.dispose();
    }
}
