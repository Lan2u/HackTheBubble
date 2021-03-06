package com.skeeter.birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.skeeter.game.Skeeter;

import java.awt.*;
import java.util.Random;

/**
 * Created by Paul Lancaster on 08/10/16
 */
public class Birdy extends Bird{
    private static final double X_SPAWN_RANGE = 400;
    private static final double Y_SPAWN_RANGE = 200;
    Texture pheasantTexture;
    public double dx, dy, ddy; // dx = horizontal velocity (pixels / second)
    private double acc;
    private boolean isWavey = false;
    private double x, y;
    private Random randomGenerator = new Random();

    public Birdy(Texture t, int x, int y, int width, int h){
        dx = -40; // Velocity
        acc = -1; // Rate the bird speeds up (carries across bird death)
        pheasantTexture = t;
        this.x = x;
        this.y = y;
        setBounds(x,y,width,h);
    }

    @Override
    public void kill(){
        died();
    }

    public Rectangle getRect(){
        return this;
    }

    @Override
    public void hit(){
        // called when bird is hit
        Skeeter.score++;
        died();
    }

    private void died(){
        if (Math.random() < 0.9){
            isWavey = false;
        }else{
            isWavey = true;
        }
        x = 700 + Math.random() * X_SPAWN_RANGE;
        y = 200 + Math.random() * Y_SPAWN_RANGE;
    }

    @Override
    public void update(float dT){
        if (!isWavey) { // Linear path
            // Every frame
            ddy = 0;
        }else{ // Non linear path
            if (y <200){
                ddy = 0.5 + (randomGenerator.nextInt(8)/10.0);
            }else{
                ddy = -0.5 - (randomGenerator.nextInt(8)/10.0);
            }
        }

        dy += ddy;
        y += dy;
        dx += acc * dT;
        x = x + dx * dT;
        //y = y + dy * dT;
        if (y <= 0 || y >= 450){
            died();
        }

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
