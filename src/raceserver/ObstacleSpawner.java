package raceserver;

import packets.PacketObstacle;

public class ObstacleSpawner extends Thread{
	Server server;
	public ObstacleSpawner(Server server) {
		this.server = server;
	}
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 100000000000D/60D;
		double change = 0;
		while(true) {
			long currentTime = System.nanoTime();
			change += (currentTime - lastTime)/nsPerTick;
			lastTime = currentTime;
			
			while(change >= 1) {
				spawn();
				change--;
			}
		}
	}
	
	private void spawn() {
		server.sendDataAll(new PacketObstacle());
	}
}
