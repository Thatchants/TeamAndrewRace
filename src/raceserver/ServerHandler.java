package raceserver;

import java.net.InetAddress;

import packets.PacketDisconnect;
import packets.PacketLogin;

public class ServerHandler extends Thread{
	public Server server;
	private ObstacleSpawner spawner;
	public PlayerMP[] players;
	private ServerWindow window;
	public ServerHandler() {
		players = new PlayerMP[2];
		server = new Server(this);
		spawner = new ObstacleSpawner(server, this);
		window = new ServerWindow("Server", this);
	}
	
	private void init() {
		server.start();
		spawner.start();
	}
	
	public void run() {
		init();
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		double change = 0;
		while(true) {
			long currentTime = System.nanoTime();
			change += (currentTime - lastTime)/nsPerTick;
			lastTime = currentTime;
			
			while(change >= 1) {
				window.graphics.repaint();
				change--;
			}
		}
	}
	
	public void addPlayer(PacketLogin packet, InetAddress address, int port) {
		for(int i = 0;i < 2;i++) {
			if(players[i] == null) {
				int other = i==0 ? 1 : 0;
				if(!(players[other] != null && packet.getUsername().equals(players[other].username))) {
					players[i] = new PlayerMP(packet, address, port);
					System.out.println(players[i].username + " logged in as player " + (i+1));
					server.sendData(packet, address, port);
				}
			}
		}
	}

	public void removePlayer(PacketDisconnect packet, InetAddress address, int port){
		for(int i = 0;i<2;i++){
			if(players[i] != null && players[i].username.equals(packet.getUsername())){
				System.out.println(players[i].username + " disconnected as player " + (i+1));
				players[i] = null;
				server.sendData(packet, address, port);
			}
		}
	}
	
	public static void main(String[] args) {
		new ServerHandler().start();
	}
}
