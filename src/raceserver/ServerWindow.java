package raceserver;

import packets.PacketDisconnect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ServerWindow extends JFrame implements WindowListener {

    ServerHandler serverHandler;
    private DisconnectButton button0, button1;
    public CustomGraphics graphics;

    public ServerWindow(String title, ServerHandler serverHandler) {
        super(title);
        this.serverHandler = serverHandler;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setPreferredSize(new Dimension(500, 500));
        graphics = new CustomGraphics(this);

        button0 = new DisconnectButton(0, serverHandler, 500 / 2 - 50, 500 / 4 - 10, 100, 20, Color.GRAY);
        button1 = new DisconnectButton(1, serverHandler, 500 / 2 - 50, 500 - 500 / 4 - 10, 100, 20, Color.GRAY);

        JLayeredPane layers = new JLayeredPane();
        layers.setPreferredSize(new Dimension(500, 500));
        layers.add(graphics, JLayeredPane.DEFAULT_LAYER);
        layers.add(button0, JLayeredPane.POPUP_LAYER);
        layers.add(button1, JLayeredPane.POPUP_LAYER);
        setContentPane(layers);
        pack();
        setResizable(false);
        setVisible(true);
        addWindowListener(this);
    }




    @Override
    public void windowClosing(WindowEvent e) {
        for (int index = 0; index <= 1; index++)
            if (serverHandler.players[index] != null) {
                PacketDisconnect packet = new PacketDisconnect(serverHandler.players[index].username);
                serverHandler.server.sendData(packet, serverHandler.players[index].address, serverHandler.players[index].port);
                serverHandler.removePlayer(packet, serverHandler.players[index].address, serverHandler.players[index].port);
            }
    }
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
