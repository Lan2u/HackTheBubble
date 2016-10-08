package com.skeeter.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.awt.*;

/**
 * Created by Paul Lancaster on 08/10/16
 */
public class Shooter extends GameSprite{

    private static final double GUN_FIRE_ANIMATION_TIME = 4; // In seconds

    private final Point GUN_POINT = new Point(40,30); // Gun position

    private static final int GUN_WIDTH = 60;
    private static final int GUN_HEIGHT = 10;

    private final int X_POS = 20; // The shooter doesn't move so theses are constants
    private final int Y_POS = 20;

    boolean gunFiring = false;

    private double gunAngle;

    private Texture guyTex;
    private Texture gunTex;
    private double fireTimeLeft; // time until gun is finished firing

    public Shooter(Texture guyTex, Texture gunTex) {
        this.guyTex = guyTex;
        this.gunTex = gunTex;
        gunAngle = 0.5;
    }

    private void simpleGunDraw(Batch batch, Texture tex){ // Called to draw the gun, Simple because it only needs the texture
        int rotation = (int) Math.round(360.0 - gunAngle);

        batch.draw(tex, GUN_POINT.x, GUN_POINT.y, GUN_POINT.x, GUN_POINT.y + (GUN_HEIGHT/2),GUN_WIDTH,GUN_HEIGHT,
                1f,1f,rotation,0,0,GUN_WIDTH,GUN_HEIGHT,false,false);
    }

    @Override
    public void draw(Batch batch){
        batch.draw(guyTex, X_POS,Y_POS);
        simpleGunDraw(batch,gunTex);
        if (gunFiring){
            // Draw a fire animation on the end of the gun
        }
    }

    // @param deltaT , time between this and the last frame in seconds?
    public void update(double deltaT, Point mouseAim){
        gunAngle = calculateAngle(mouseAim, GUN_POINT);

        // If the gun is firing
        if (fireTimeLeft > 0.0) {
            // Gun is currently firing
            fireTimeLeft = fireTimeLeft- deltaT; // Use the time elapsed between frames to workout how much longer it is going to be firing for
        }
        else if(fireTimeLeft < 0.0){ // Firing is finished so se the fire time left to 0
            fireTimeLeft = 0.0;
        }
    }

    // Calculate the angle of the gun (taking the gun position and the mouse position)
    // Only returns in the range 0.0-1.0
    private double calculateAngle(Point mouseAim, Point gun_point) {
        return 0.0;
    }

    // Called when gun fired
    public void gunFired(){
        // Calculate physics
        // Display animation
        fireTimeLeft = System.nanoTime() + secondsToNano(GUN_FIRE_ANIMATION_TIME);
    }

    // convert from seconds to nano seconds
    private long secondsToNano(double s) {
        return (long) ( s * Math.pow(10.0,9.0));
    }
}
