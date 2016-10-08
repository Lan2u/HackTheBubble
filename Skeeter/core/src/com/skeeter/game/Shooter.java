package com.skeeter.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.skeeter.birds.Bird;
import com.skeeter.phys.GameLogic;

import java.awt.*;

/**
 * Created by Paul Lancaster on 08/10/16
 */
public class Shooter extends GameSprite{

    private static final double GUN_FIRE_ANIMATION_TIME = 0.5; // In seconds

    private final Point GUN_POINT = new Point(40,30); // Gun position

    private static final int GUN_WIDTH = 150;
    private static final int GUN_HEIGHT = 53;

    private final int X_POS = 100; // The shooter doesn't move so theses are constants
    private final int Y_POS = 50;

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
        int rotation = (int) Math.round(gunAngle);
        batch.draw(tex, GUN_POINT.x, GUN_POINT.y, GUN_POINT.x, GUN_POINT.y,GUN_WIDTH,GUN_HEIGHT,
                1f,1f,rotation,0,0,GUN_WIDTH,GUN_HEIGHT,false,false);
    }

    @Override
    public void draw(Batch batch){
        //batch.draw(guyTex, X_POS,Y_POS);

        if (fireTimeLeft > 0){
            // Gun is firing
        }else{
            // Gun isn't firing
            simpleGunDraw(batch,gunTex);
        }
        drawDebug(batch);

    }

    private void drawDebug(Batch batch) {
        Texture debugTex = new Texture("/cs/home/pl59/HackTheBubble/Skeeter/core/assets/debug.png");
        int rotation = (int) Math.round(gunAngle);
        batch.draw(debugTex, GUN_POINT.x, GUN_POINT.y, GUN_POINT.x, GUN_POINT.y,1000,3,
                1f,1f,rotation,0,0,1000,3,false,false);
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

        gunAngle = calculateAngle(mouseAim,GUN_POINT);
    }

    // Calculate the angle of the gun (taking the gun position and the mouse position)
    // Only returns in the range 0.0-1.0
    private double calculateAngle(Point mouseAim, Point gun_point) {
        // Calculate angle
        // Return it in degrees
        //http://stackoverflow.com/questions/3449826/how-do-i-find-the-inverse-tangent-of-a-line
        double dX = mouseAim.getX() - gun_point.getX();
        double dY = gun_point.getY() + 360 - mouseAim.getY();

        double angle = Math.atan2(dY, dX);
        angle = (angle * 180.0)/Math.PI;
        if(angle>90)angle=90;
        if(angle<-30)angle=-30;
        return angle;
    }

    // Called when gun fired
    public void gunFired(){
        if (!(fireTimeLeft > 0)) {
            // Calculate physics
            for (GameSprite bird : Skeeter.sprites) { // For every bird
                if (GameLogic.birdShot((Bird) bird, gunAngle)) { // If it was hit by the gun
                    bird.hit(); // Bird was hit
                } else {
                    // Missed
                }
            }
            // Display animation
            fireTimeLeft = GUN_FIRE_ANIMATION_TIME;
        }
    }
}
