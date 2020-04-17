package raceclient;

import packets.*;

public class Race extends Thread{
	private Client client;
	public Race() {
		client = new Client(this, "localhost");
	}
	
	public void init() {
		client.start();
		PacketLogin packet = new PacketLogin("andrew");
		client.sendData(packet);
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
