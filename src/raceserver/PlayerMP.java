package raceserver;

import java.net.InetAddress;

import packets.*;

public class PlayerMP {
	
	public String username;
	public InetAddress address;
	public int port;
	
	public PlayerMP(PacketLogin packet, InetAddress address, int port) {
		username = packet.getUsername();
		this.address = address;
		this.port = port;
	}
}
