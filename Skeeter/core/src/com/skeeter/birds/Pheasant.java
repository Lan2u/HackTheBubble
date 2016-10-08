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
    public int dx, dy; // dx = horizontal velocity (pixels / second)

    public Pheasant(Texture t, int x, int y, int width, int h){
        pheasantTexture = t;
        setBounds(x,y,width,h);
    }

    public Rectangle getRect(){
        return this;
    }

    @Override
    public void hit(){
        // called when bird is hit
        System.out.println(this.toString() + " was hit");
        //dispose();
    }

    @Override
    public void update(float dT){
        // Every frame
        x = (int) (x + dx * dT);
        if (x <= 0){
            Skeeter.gameOver();
        }
    }

    @Override
    public void draw(Batch batch){
        batch.draw(pheasantTexture,(int)getX(),(int)getY(), (int) getWidth(), (int) getHeight());
    }


}
