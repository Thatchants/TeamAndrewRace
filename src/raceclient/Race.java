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
	public int gameLostTicks = 0;
	public int gameWonTicks = 0;


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
				if(gameLostTicks > 0)gameLostTicks --;
				if(gameWonTicks > 0)gameWonTicks --;
				if(gameRunning) {
					for(Iterator<Entity> itr = entities.iterator(); itr.hasNext();){
						Obstacle e = (Obstacle)itr.next();
						e.tick();
						if(e.killMe || gameLostTicks > 0 || gameWonTicks > 0){// or gamewonticks >0
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
		for(int i = 0;i < packet.getObstacleInfo().length;i+= 3){
			double x = Double.parseDouble(packet.getObstacleInfo()[i]);
			double y = Double.parseDouble(packet.getObstacleInfo()[i+1]);
			boolean isPlatform = Boolean.parseBoolean(packet.getObstacleInfo()[i+2]);
			entities.add(new Obstacle(x, y, player, this, isPlatform));
		}
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

	public void loseGame(){
		client.sendData(new PacketLoseWin(username));
		gameLostTicks = 100;
	}

	public void winGame(){
		gameWonTicks = 100;
	}
	
	public static void main(String[] args) {
		new Race().start();
	}
}
