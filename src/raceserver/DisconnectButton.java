package raceserver;

import packets.PacketDisconnect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DisconnectButton extends JPanel implements MouseListener {
    private int index;
    private ServerHandler serverHandler;
    private Color color;

    public DisconnectButton(int index, ServerHandler serverHandler, int x, int y, int width, int height, Color color) {
        this.index = index;
        this.serverHandler = serverHandler;
        this.color = color;
        setBounds(x, y, width, height);
        addMouseListener(this);
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.YELLOW);
        CustomGraphics.drawCenteredString(g,"Kick Player",new Rectangle(0,0,getWidth(),getHeight()),g.getFont());
    }




    @Override
    public void mouseClicked(MouseEvent e) {
        if (serverHandler.players[index] != null) {
            PacketDisconnect packet = new PacketDisconnect(serverHandler.players[index].username);
            serverHandler.server.sendData(packet, serverHandler.players[index].address, serverHandler.players[index].port);
            serverHandler.removePlayer(packet, serverHandler.players[index].address, serverHandler.players[index].port);
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
