package raceserver;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ServerWindow extends JFrame {

    ServerHandler server;
    BufferedImage image;
    public ServerWindow(String title, ServerHandler server){
        super(title);
        this.server = server;
        Graphics g = this.getGraphics();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
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
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
        g2d.setColor(Color.YELLOW);
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        for(int i = 0;i < 2;i++){
            if(server.players[i] != null) {
                g2d.drawString("Player " + (i+1) + ":" + server.players[i].username, 10, 50 + i*25);
            }else{
                g2d.drawString("Player " + (i+1) + " is empty", 10, 50 + i*25);
            }
        }
        g2d.dispose();
    }
}
