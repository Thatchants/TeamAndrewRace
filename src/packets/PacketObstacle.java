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

	@Override
	public String getUsername() {
		return null;
	}

	public String getY() {
		return y;
	}
}
