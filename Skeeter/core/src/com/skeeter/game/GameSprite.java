package com.skeeter.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Paul Lancaster on 08/10/16
 */
public class GameSprite extends Sprite {

    @Override
    public void draw(Batch batch){
        System.out.println("ERROR SOMETHING DIDN'T OVERWRITE DRAW");
    }

    public void update(float deltaT){

    }

    public void dispose(){

    }

    public void hit() {
        System.out.println("hit");
    }
}
