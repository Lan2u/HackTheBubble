package com.skeeter.birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by Paul Lancaster on 08/10/16
 */
public class Pheasant extends Bird{
    Texture pheasantTexture;
    public int x, y, dx, dy; // dx = horizontal velocity (pixels / second)

    public Pheasant(Texture t){
        pheasantTexture = t;
        x = 800;
        y = 400;
        dx = -4;
        dy = 0;
        setSize(100,100);
    }

    @Override
    public void hit(){
        // called when bird is hit
        System.out.println(this.toString() + " was hit");
        dispose();
    }

    @Override
    public void update(float dT){
        // Every frame
        x = (int) (x + dx * dT);
    }

    @Override
    public void draw(Batch batch){
        batch.draw(pheasantTexture,x,y, getWidth(), getHeight());
    }


}
