package raceclient;

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
			e.printStackTrace();
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
			System.out.flush();
			System.out.println("Received Pong from Server");
		}else if(type.equals("1")) {
			PacketLogin packet = new PacketLogin(data);
			System.out.println("Logged in as " + packet.getUsername());
		}else if(type.equals("2")){
			System.out.println("Disconnected");
		}else if(type.equals("3")) {
			race.receiveObstacle();
		}
	}
	
	public void sendData(Packet packet) {
		byte[] data = packet.getData();
		DatagramPacket sendPacket = new DatagramPacket(data, data.length, serverAddress, 25000);
		try {
			this.socket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
