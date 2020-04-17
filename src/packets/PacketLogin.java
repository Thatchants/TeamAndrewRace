package packets;

public class PacketLogin extends Packet{
	
	private String username;
	
	public PacketLogin(String username) {
		this.username = username;
	}
	
	public PacketLogin(byte[] data) {
		String[] dataElements = Packet.readData(data).split(",");
		this.username = dataElements[0];
	}
	
	public byte[] getData() {
		return ("1" + username).getBytes();
	}
	
	public String getUsername() {
		return username;
	}
}
