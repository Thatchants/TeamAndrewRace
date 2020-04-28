package raceclient.entities;

import java.awt.*;

public abstract class Entity {
    protected double x, y;
    protected int width, height;
    protected Color color;
    protected double xVel = 0, yVel = 0;
    public Entity(double x, double y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void updateLocation(){
        x += xVel;
        y += yVel;
    }

    public void paintEntity(Graphics2D g2d, int yOffset){
        g2d.setColor(color);
        g2d.fillRect((int)x, (int)y - yOffset, width, height);
    }

    public abstract void tick();
}
