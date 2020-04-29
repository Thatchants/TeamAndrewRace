package raceclient.display;

import packets.PacketDisconnect;
import packets.PacketLogin;
import raceclient.Race;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RaceWindow extends JFrame implements WindowListener, KeyListener {

    public boolean loggedIn = false;

    public Race race;
    public IPInput ipInput;
    public CustomGraphics graphics;
    public UsernameInput usernameInput;
    private JoinButton joinButton;
    private JLayeredPane layers;

    public RaceWindow(String title, Race race) {
        super(title);
        this.race = race;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setPreferredSize(new Dimension(500, 500));

        ipInput = new IPInput(this, 0, 0, 500, 60);
        usernameInput = new UsernameInput(this, 0, 70, 500, 60);
        graphics = new CustomGraphics(this);

        joinButton = new JoinButton(this, 500 / 2 - 50, 300, 100, 50, Color.GRAY);

        layers = new JLayeredPane();
        layers.setPreferredSize(new Dimension(500, 500));
        layers.add(graphics, JLayeredPane.DEFAULT_LAYER);
        layers.add(ipInput, JLayeredPane.POPUP_LAYER);
        layers.add(usernameInput, JLayeredPane.POPUP_LAYER);
        layers.add(joinButton, JLayeredPane.POPUP_LAYER);
        setContentPane(layers);
        pack();
        setResizable(false);
        setVisible(true);
        addWindowListener(this);
        addKeyListener(this);
        setFocusable(true);
    }

    public void loggedIn() {
        loggedIn = true;
        layers.remove(joinButton);
        layers.remove(ipInput);
        layers.remove(usernameInput);
    }

    public void loggedOut() {
        if (loggedIn != false) {
            loggedIn = false;
            layers.add(ipInput, JLayeredPane.POPUP_LAYER);
            layers.add(usernameInput, JLayeredPane.POPUP_LAYER);
            layers.add(joinButton, JLayeredPane.POPUP_LAYER);
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (loggedIn == true)
            race.disconnect();
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

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    private boolean spacePressed = false;

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyChar() == ' ' && spacePressed == false){
            spacePressed = true;
            if(loggedIn) {
                race.player.jump();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(keyEvent.getKeyChar() == ' ') {
            spacePressed = false;
        }
    }
}
