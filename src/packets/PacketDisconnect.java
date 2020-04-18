package packets;

public class PacketDisconnect extends Packet{

    private String username;

    public PacketDisconnect(String username) {
        this.username = username;
    }

    public PacketDisconnect(byte[] data) {
        String[] dataElements = Packet.readData(data).split(",");
        this.username = dataElements[0];
    }

    public byte[] getData() {
        return ("2" + username).getBytes();
    }

    public String getUsername() {
        return username;
    }
}
