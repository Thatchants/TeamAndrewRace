package packets;

public class PacketPing extends Packet{

	@Override
	public byte[] getData() {
		return ("0").getBytes();
	}

	@Override
	public String getUsername() {
		return null;
	}

}
