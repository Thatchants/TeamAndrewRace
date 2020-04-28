package raceclient.entities;

import packets.PacketPlayerInfo;

import java.awt.*;

public class OtherPlayer extends Entity{
    public OtherPlayer() {
        super(25, 460, 20, 20);
        color = Color.RED;
    }

    @Override
    public void tick() {

    }

    public void positionToPacket(PacketPlayerInfo packet){
        System.out.println(packet.getY());
        y = Double.parseDouble(packet.getY());
    }
}
