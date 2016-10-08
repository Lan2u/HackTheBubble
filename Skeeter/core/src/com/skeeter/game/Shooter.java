package com.skeeter.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by Paul Lancaster on 08/10/16
 */
public class Shooter extends GameSprite{
    private static final int GUN_Y = 40;
    private static final int GUN_X = 30;

    private static final int GUN_WIDTH = 60;
    private static final int GUN_HEIGHT = 10;

    private final int X_POS = 20; // The shooter doesn't move so theses are constants
    private final int Y_POS = 20;

    boolean gunFiring = false;

    private double gunAngle;

    private Texture guyTex;
    private Texture gunTex;

    public Shooter(Texture guyTex, Texture gunTex) {
        this.guyTex = guyTex;
        this.gunTex = gunTex;
        gunAngle = 0.5;
    }

    private void simpleGunDraw(Batch batch, Texture tex){ // Called to draw the gun, Simple because it only needs the texture
        int rotation = (int) Math.round(360.0 - gunAngle);

        batch.draw(tex, GUN_X, GUN_Y, GUN_X, GUN_Y + (GUN_HEIGHT/2),GUN_WIDTH,GUN_HEIGHT,
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

    public void update(){

    }

    // Called when gun fired
    public void gunFired(){

    }
}
