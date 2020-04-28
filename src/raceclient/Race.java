package raceclient;

import packets.*;
import raceclient.display.RaceWindow;
import raceclient.entities.Entity;
import raceclient.entities.Obstacle;
import raceclient.entities.OtherPlayer;
import raceclient.entities.Player;

import java.awt.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

public class Race extends Thread{
	private Client client;
	public RaceWindow window;

	public ArrayList<Entity>  entities= new ArrayList<>();
	public Player player;
	public OtherPlayer otherPlayer;
	private boolean gameRunning = false;


	public String username = "default username";
	public Race() {
		client = new Client(this, "default");
		window = new RaceWindow("Race", this);
	}
	
	public void init() {
		client.start();
	}
	
	public void run() {
		init();
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		double change = 0;
		String lastIPCheck = "";
		while(true) {
			long currentTime = System.nanoTime();
			change += (currentTime - lastTime)/nsPerTick;
			lastTime = currentTime;
			
			while(change >= 1) {
				if(gameRunning) {
					for(Iterator<Entity> itr = entities.iterator(); itr.hasNext();){
						Obstacle e = (Obstacle)itr.next();
						e.tick();
						if(e.killMe){
							itr.remove();
						}
					}
					player.tick();
					client.sendData(player.generateLocationPacket(username));
				}
				window.graphics.repaint();
				if(!window.loggedIn && !lastIPCheck.equals(window.ipInput.getText())){
					window.ipInput.setColor(Color.RED);
					lastIPCheck = window.ipInput.getText();
					try {
						client.changeServerIP(lastIPCheck);
						client.sendData(new PacketPing());
					} catch (UnknownHostException e) {
					}
				}
				change--;
			}
		}
	}
	public void receiveObstacle(PacketObstacle packet) {
		entities.add(new Obstacle(Double.parseDouble(packet.getY()), player));
	}

	public void tryLogin(String username){
		PacketLogin packet = new PacketLogin(username);
		client.sendData(packet);
	}

	public void disconnect(){
		PacketDisconnect packet = new PacketDisconnect(username);
		client.sendData(packet);
	}

	public void startGame(){
		player = new Player();
		otherPlayer = new OtherPlayer();
		gameRunning = true;
	}
	
	public static void main(String[] args) {
		new Race().start();
	}
}
