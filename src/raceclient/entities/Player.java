package raceclient.entities;

import packets.PacketPlayerInfo;

import java.awt.*;

public class Player extends Entity{

    protected double prevY;
    protected double prevYVel;
    protected boolean grounded = true;

    public Player() {
        super(25, 460, 20, 20);
        color = Color.BLUE;
    }

    @Override
    public void tick() {
        prevY = y;
        prevYVel = yVel;
        updateLocation();
        if(!grounded)yVel += .7;
        if(y >= 460)ground(480);
    }

    public void jump(){
        System.out.println("yvel: " + yVel + " y: " + y + " grounded: " + grounded);
        if(grounded){
            this.yVel = -10;
            deGround();
        }
    }

    public void ground(double y){
        //TODO send server a packet
        grounded = true;
        this.y = y - height;
        yVel = 0;
    }

    public void deGround(){
        //TODO send server a packet
        grounded = false;
    }

    public PacketPlayerInfo generateLocationPacket(String username){
        return new PacketPlayerInfo("" + y, username);
    }
}
