package packets;

public abstract class Packet {
	public Packet() {
		
	}
	
	public abstract byte[] getData();
	
	public static String readData(byte[] data) {
		String message = new String(data).trim();
		return message.substring(1);
	}
}
