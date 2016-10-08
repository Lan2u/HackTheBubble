package com.skeeter.birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.skeeter.game.Skeeter;

import java.awt.*;

/**
 * Created by Paul Lancaster on 08/10/16
 */
public class PheasantOnSteroids extends Bird{
    private Texture bTexture;

    private double x;
    private double y;
    private double yVelocity = 5;
    private double yAcceleration = 0.2;

    public PheasantOnSteroids(Texture t, int x, int y, int width, int h){
        bTexture = t;
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
        yVelocity = yVelocity - yAcceleration;
        this.y = this.y + yVelocity * dT;
        this.x = this.x - 15 * dT;
        if (y < 100) {
            yVelocity = 5;
        }
    }


    @Override
    public void draw(Batch batch){
        System.out.println("HIISDISOD");
        batch.draw(bTexture,(int) x,(int) y,(int) getWidth(), (int) getHeight());
    }


}
