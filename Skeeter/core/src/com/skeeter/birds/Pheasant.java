package com.skeeter.birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.skeeter.game.Skeeter;

import java.awt.*;

/**
 * Created by Paul Lancaster on 08/10/16
 */
public class Pheasant extends Bird{
    Texture pheasantTexture;
    public double dx, dy; // dx = horizontal velocity (pixels / second)
    private double acc;
    private double x, y;
    private double timeSinceStart = 0.0;

    public Pheasant(Texture t, int x, int y, int width, int h){
        dx = -40; // Velocity
        acc = -0.2; // Rate the bird speeds up (carries across bird death)
        pheasantTexture = t;
        this.x = x;
        this.y = y;
        setBounds(x,y,width,h);
    }

    public Rectangle getRect(){
        return this;
    }

    @Override
    public void hit(){
        // called when bird is hit
        System.out.println(this.toString() + " was hit");
        this.x = 900;
    }

    @Override
    public void update(float dT){

        // Every frame
        x = x + dx * dT;
        y = y + dy * dT;

        timeSinceStart =+ dT;
        dx += acc;

        System.out.println(dx);
        setLocation((int)x,(int)y);

        if (getX() <= 0){
            // Lost
            Skeeter.gameOver();
        }
    }

    @Override
    public void draw(Batch batch){
        batch.draw(pheasantTexture,(int) x,(int) y,(int) getWidth(), (int) getHeight());
    }


}
