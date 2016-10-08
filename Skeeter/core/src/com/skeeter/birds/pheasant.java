package com.skeeter.birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by Paul Lancaster on 08/10/16
 */
public class pheasant extends Bird{
    Texture pheasantTexture;
    public int x, y, width, height;

    pheasant(Texture t){
        pheasantTexture = t;
    }

    @Override
    public void hit(){
        // called when bird is hit
    }

    @Override
    public void update(float dT){
        // Every frame
    }

    @Override
    public void draw(Batch batch){
        batch.draw(pheasantTexture,x,y);
    }
}
