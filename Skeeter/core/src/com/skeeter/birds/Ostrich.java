package com.skeeter.birds;

import com.skeeter.game.Bird;

/**
 * Created by fc53 on 08/10/16.
 */
public class Ostrich extends Bird {

    public void main(){
        setHitPoints(3);

    }

    @Override
    public void update(long deltaT){
        positionX += velocityX;
    }
}
