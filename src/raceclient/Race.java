package raceclient;

import packets.*;
import raceclient.display.RaceWindow;
import raceclient.entities.Entity;
import raceclient.entities.Obstacle;
import raceclient.entities.Player;

import java.awt.*;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Race extends Thread{
	private Client client;
	public RaceWindow window;

	public ArrayList<Entity>  entities= new ArrayList<>();
	public Player player;
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
					for (Entity e : entities) {
						e.tick();
					}
					player.tick();
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
	private int obstacles = 0;
	public void receiveObstacle() {
		obstacles++;
		//System.out.println(obstacles);
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
		entities.add(new Obstacle(440, player));
		gameRunning = true;
	}
	
	public static void main(String[] args) {
		new Race().start();
	}
}
