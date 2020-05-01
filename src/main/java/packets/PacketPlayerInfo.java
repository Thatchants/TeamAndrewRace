package packets;

public class PacketPlayerInfo extends Packet{

    private String y;
    private String username;

    public PacketPlayerInfo(String y, String username) {
        this.y = y;
        this.username = username;
    }

    public PacketPlayerInfo(byte[] data) {
        String[] dataElements = Packet.readData(data).split(",");
        this.y = dataElements[0];
        this.username = dataElements[1];
    }

    public byte[] getData() {
        return ("4" + y + "," + username).getBytes();
    }

    public String getY() {
        return y;
    }

    public String getUsername() {
        return username;
    }
}
