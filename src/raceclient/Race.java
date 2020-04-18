package raceclient;

import packets.*;
import raceclient.display.RaceWindow;

import java.net.UnknownHostException;

public class Race extends Thread{
	private Client client;
	public RaceWindow window;
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
		while(true) {
			long currentTime = System.nanoTime();
			change += (currentTime - lastTime)/nsPerTick;
			lastTime = currentTime;
			
			while(change >= 1) {
				window.repaint();
				if(!window.loggedIn){
					try {
						client.changeServerIP(window.ipInput.getText());
						client.sendData(new PacketPing());
						/*
						If client receives ping packet, tell the RaceWindow.

						Put the IPInput into the RaceWindow

						IPInput is its own class cause possibly i can style it there?
						 */
					} catch (UnknownHostException e) {
						window.validIP = false;
						e.printStackTrace();
					}
				}
				change--;
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
