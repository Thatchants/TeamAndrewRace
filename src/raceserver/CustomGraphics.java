package raceserver;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

public class CustomGraphics extends JPanel {
    private BufferedImage image;
    private ServerWindow window;
    private String publicIP = "";

    public CustomGraphics(ServerWindow window) {
        this.window = window;
        setSize(new Dimension(500, 500));
        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        try {
            publicIP = getPublicIP();
        }catch(Exception e){}
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void paint(Graphics g) {
        updateImage();
        g.drawImage(image, 0, 0, null);
    }

    private void updateImage() {
        Graphics2D g2d = image.createGraphics();
        if (window.serverHandler.players[0] == null)
            g2d.setColor(new Color(128, 0, 0));
        else
            g2d.setColor(new Color(0, 128, 0));
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight() / 2);
        if (window.serverHandler.players[1] == null)
            g2d.setColor(new Color(128, 0, 0));
        else
            g2d.setColor(new Color(0, 128, 0));
        g2d.fillRect(0, image.getHeight() / 2, image.getWidth(), image.getHeight() / 2);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, image.getHeight() / 2 - 10, image.getWidth(), 20);
        g2d.setColor(Color.YELLOW);
        drawCenteredString(g2d,publicIP,new Rectangle(0,0,getWidth(),getHeight()),g2d.getFont());
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        for (int i = 0; i < 2; i++) {
            if (window.serverHandler.players[i] != null) {
                g2d.drawString("Player " + (i + 1) + ": " + window.serverHandler.players[i].username, image.getWidth() / 2 - (g2d.getFontMetrics().stringWidth("Player " + (i + 1) + ": " + window.serverHandler.players[i].username)) / 2, 50 + i * (image.getHeight() / 2 + 10));
            } else {
                g2d.drawString("Player " + (i + 1) + " not found", image.getWidth() / 2 - (g2d.getFontMetrics().stringWidth("Player " + (i + 1) + " not found")) / 2, 50 + i * (image.getHeight() / 2 + 10));
            }
        }
        g2d.dispose();
    }

    public static void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }

    private String getPublicIP() throws IOException {
        URL ipWebsite = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(ipWebsite.openStream()));
        String ip = in.readLine();
        return ip;
    }
}
