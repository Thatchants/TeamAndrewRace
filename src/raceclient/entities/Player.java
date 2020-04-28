package raceclient.entities;

import java.awt.*;

public class Player extends Entity{

    protected double prevY;
    protected double prevYVel;
    protected boolean grounded = true;

    public Player() {
        super(25, 470, 10, 10);
        color = Color.BLUE;
    }

    @Override
    public void tick() {
        prevY = y;
        prevYVel = yVel;
        updateLocation();
        if(y > 470)ground(480);
    }

    public void jump(){
        System.out.println("jump");
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
}
