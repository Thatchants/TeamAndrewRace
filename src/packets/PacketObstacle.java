package packets;

public class PacketObstacle extends Packet{

	private String y;

	public PacketObstacle(String y) {
		this.y = y;
	}

	public PacketObstacle(byte[] data) {
		String[] dataElements = Packet.readData(data).split(",");
		this.y = dataElements[0];
	}

	public byte[] getData() {
		return ("3" + y).getBytes();
	}

	public String getY() {
		return y;
	}
}
