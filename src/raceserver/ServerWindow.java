package raceserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class ServerWindow extends JFrame {

    ServerHandler serverHandler;
    private DisconnectButton button0, button1;
    public CustomGraphics graphics;

    public ServerWindow(String title, ServerHandler serverHandler) {
        super(title);
        this.serverHandler = serverHandler;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setPreferredSize(new Dimension(500, 500));
        graphics = new CustomGraphics(this);

        button0 = new DisconnectButton(0, serverHandler, 500/2-50, 500/4-10, 100, 20, Color.GRAY);
        button1 = new DisconnectButton(1, serverHandler, 500/2-50, 500-500/4-10, 100, 20, Color.GRAY);

        JLayeredPane layers = new JLayeredPane();
        layers.setPreferredSize(new Dimension(500, 500));
        layers.add(graphics, JLayeredPane.DEFAULT_LAYER);
        layers.add(button0, JLayeredPane.POPUP_LAYER);
        layers.add(button1, JLayeredPane.POPUP_LAYER);
        setContentPane(layers);
        pack();
        setResizable(false);
        setVisible(true);
    }


}
