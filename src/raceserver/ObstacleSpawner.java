package raceserver;

import packets.PacketObstacle;

public class ObstacleSpawner extends Thread{
	Server server;
	ServerHandler serverHandler;
	public ObstacleSpawner(Server server, ServerHandler serverHandler) {
		this.server = server;
		this.serverHandler = serverHandler;
	}
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		double change = 0;
		int ticksToNextCombo = 0;
		while(true) {
			long currentTime = System.nanoTime();
			change += (currentTime - lastTime)/nsPerTick;
			lastTime = currentTime;
			while(change >= 1) {
				if(ticksToNextCombo <= 0) {
					ticksToNextCombo = spawn();
				}else{
					ticksToNextCombo --;
				}
				change--;
			}
		}
	}
	
	private int spawn() {
		if(serverHandler.players[0] != null && serverHandler.players[1] != null) {
			String[] combo = ObstacleCombos.randomCombo();
			server.sendDataAll(new PacketObstacle(combo));
			return ObstacleCombos.comboSize(combo)/2 + 50;
		}
		return 0;
	}
}
