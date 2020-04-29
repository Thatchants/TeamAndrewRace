package raceclient.entities;

import raceclient.Race;

import java.awt.*;

public class Obstacle extends Entity{

    private double prevX;
    private boolean playerGrounded = false;
    private Player player;
    public boolean killMe = false;
    private Race race;

    public Obstacle(double y, Player player, Race race) {
        super(490, y, 25, 25);
        this.xVel = -2;
        prevX = x -xVel;
        color = Color.BLACK;
        this.player = player;
        this.race = race;
    }

    @Override
    public void tick() {
        if(playerGrounded && player.y + player.height < y)playerGrounded = false;
        if((x >= player.x && x < player.x + player.width) || (player.x >= x && player.x < x + width)){//Obstacle is in the player's possible area
            if((y >= player.y && y < player.y + player.height) || (player.y >= y && player.y < y + height)){//They are inside of each other
                handleCollision();
            }
        }else if(playerGrounded && x+width < player.x){//moved out of player's region
            player.deGround();
        }
        prevX = x;
        updateLocation();
        if(x + width <=0)killMe = true;
    }

    private void handleCollision(){
        if(player.prevY + player.height <= y){
            double playerDist = (y -(player.prevY + player.height))/player.prevYVel;
            double obstacleDist = -(prevX - (player.x + player.width))/xVel;
            if(playerDist > obstacleDist){
                playerGrounded = true;
                player.ground(y);
                return;
            }
        }else{
            race.loseGame();
        }
    }
}
