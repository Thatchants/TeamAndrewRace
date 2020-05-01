package packets;

public class PacketLoseWin extends Packet{

    private String username;

    public PacketLoseWin(String username) {
        this.username = username;
    }

    public PacketLoseWin(byte[] data) {
        String[] dataElements = Packet.readData(data).split(",");
        this.username = dataElements[0];
    }

    public byte[] getData() {
        return ("5" + username).getBytes();
    }

    public String getUsername() {
        return username;
    }
}
