package raceclient;

import packets.*;
import raceclient.display.RaceWindow;

import java.awt.*;
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
		String lastIPCheck = "";
		while(true) {
			long currentTime = System.nanoTime();
			change += (currentTime - lastTime)/nsPerTick;
			lastTime = currentTime;
			
			while(change >= 1) {
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
		System.out.println(obstacles);
	}
	
	public static void main(String[] args) {
		new Race().start();
	}
}
