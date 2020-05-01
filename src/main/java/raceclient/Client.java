package raceclient;

import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import packets.*;

public class Client extends Thread{
	
	private InetAddress serverAddress;
	private DatagramSocket socket;
	private Race race;
	
	public Client(Race race, String serverAddress) {
		try {
			this.socket = new DatagramSocket();
			this.serverAddress = InetAddress.getByName(serverAddress);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			//e.printStackTrace();
		}
		this.race = race;
		
	}
	
	public void run() {
		while(true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
		}
	}
	
	private void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
		String type = message.substring(0,1);
		if(type.equals("0")) {//Ping
			race.window.ipInput.setColor(Color.GREEN);
		}else if(type.equals("1")) {
			PacketLogin packet = new PacketLogin(data);
			race.startGame();
			race.window.loggedIn();
		}else if(type.equals("2")){
			race.window.loggedOut();
		}else if(type.equals("3")) {
			race.receiveObstacle(new PacketObstacle(data));
		}else if(type.equals("4")){
			race.otherPlayer.positionToPacket(new PacketPlayerInfo(data));
		}else if(type.equals("5")){
			race.winGame();
		}
	}
	
	public void sendData(Packet packet) {
		byte[] data = packet.getData();
		DatagramPacket sendPacket = new DatagramPacket(data, data.length, serverAddress, 25000);
		try {
			this.socket.send(sendPacket);
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}

	public void changeServerIP(String address) throws UnknownHostException {
		serverAddress = InetAddress.getByName(address);
	}
}
