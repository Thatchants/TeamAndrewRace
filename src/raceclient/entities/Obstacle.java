package raceclient.entities;

import java.awt.*;

public class Obstacle extends Entity{

    private double prevX;
    private boolean playerGrounded = false;
    private Player player;

    public Obstacle(int y, Player player) {
        super(490, y, 25, 25);
        this.xVel = -2;
        prevX = x -xVel;
        color = Color.BLACK;
        this.player = player;
    }

    @Override
    public void tick() {
        if(playerGrounded && player.y + player.height < y)playerGrounded = false;
        if((x >= player.x && x < player.x + player.width) || (player.x >= x && player.x < x + width)){//Obstacle is in the player's possible area
            System.out.println("can collide");
            if((y >= player.y && y < player.y + player.height) || (player.y >= y && player.y < y + height)){//They are inside of each other
                System.out.println("inside");
                handleCollision();
            }
        }else if(playerGrounded && x+width < player.x){//moved out of player's region
            player.deGround();
        }
        prevX = x;
        updateLocation();
    }

    private void handleCollision(){
        if(player.prevY + player.height <= y){
            double playerDist = (y -(player.prevY + player.height))/player.prevYVel;
            double obstacleDist = -(x - (player.x + player.width))/xVel;
            if(playerDist > obstacleDist){
                playerGrounded = true;
                player.ground(y);
                return;
            }
        }else{
            System.out.println("game over");
        }
    }
}
