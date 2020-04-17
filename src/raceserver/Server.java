package raceserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import packets.*;

public class Server extends Thread{
	private DatagramSocket socket;
	private ServerHandler serverHandler;
	public Server(ServerHandler serverHandler) {
		try {
			this.socket = new DatagramSocket(25000);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		this.serverHandler = serverHandler;
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
			System.out.println("Received Ping from Client");
			PacketPing packet = new PacketPing();
			sendData(packet, address, port);
		}else if(type.equals("1")) {
			PacketLogin packet = new PacketLogin(data);
			serverHandler.addPlayer(packet, address, port);
		}
	}
	
	public void sendData(Packet packet, InetAddress address, int port) {
		byte[] data = packet.getData();
		DatagramPacket sendPacket = new DatagramPacket(data, data.length, address, port);
		try {
			this.socket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendDataAll(Packet packet) {
		byte[] data = packet.getData();
		for(PlayerMP p: serverHandler.players) {
			if(p != null) {
				DatagramPacket sendPacket = new DatagramPacket(data, data.length, p.address, p.port);
				try {
					this.socket.send(sendPacket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
