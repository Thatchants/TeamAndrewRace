package raceclient.entities;

import java.awt.*;

public class Obstacle extends Entity{

    private double prevX;
    private boolean hasCollided = false;
    private Player player;

    public Obstacle(int x, int y, int width, int height, double xVel, Player player) {
        super(x, y, width, height);
        this.xVel = xVel;
        prevX = x -xVel;
        color = Color.BLACK;
        this.player = player;
    }

    @Override
    public void tick() {
        if(!hasCollided && ((x >= 50 && x < 70) || (x + width >= 50 && x + width < 70))){//Obstacle is in the player's possible area
            if((player.y < y + height && player.y >= y) || (player.y + player.height < y + height && player.y + player.height >= y)){//They are inside of each other
                handleCollision();
            }
        }else if(hasCollided && x+width < 50){//moved out of player's region
            player.deGround();
        }
        prevX = x;
        updateLocation();
    }

    private void handleCollision(){
        hasCollided = true;
    }
}
