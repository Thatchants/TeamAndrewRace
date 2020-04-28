package raceserver;

import packets.PacketObstacle;

public class ObstacleSpawner extends Thread{
	Server server;
	public ObstacleSpawner(Server server) {
		this.server = server;
	}
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		double change = 0;
		int spawnRate = 100;
		while(true) {
			long currentTime = System.nanoTime();
			change += (currentTime - lastTime)/nsPerTick;
			lastTime = currentTime;
			
			while(change >= 1) {
				if(spawnRate == 0) {
					spawn();
					spawnRate = 50;
				}
				spawnRate--;
				change--;
			}
		}
	}
	
	private void spawn() {
		server.sendDataAll(new PacketObstacle("430"));
	}
}
