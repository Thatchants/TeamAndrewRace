package raceclient;

import packets.*;

public class Race extends Thread{
	private Client client;
	public Race() {
		client = new Client(this, "70.95.208.226");
	}
	
	public void init() {
		client.start();
		String username = "andrew";
		PacketLogin packet = new PacketLogin(username);
		client.sendData(packet);
		try {
			Thread.sleep(25000);
		}catch(Exception e){

		}
		PacketDisconnect disconnect = new PacketDisconnect(username);
		client.sendData(disconnect);
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
			}
		}
	}
	private int obstacles = 0;
	public void receiveObstacle() {
		obstacles++;
		System.out.println(obstacles);
	}
	
	public static void main(String[] args) {
		new Race().start();
	}
}
