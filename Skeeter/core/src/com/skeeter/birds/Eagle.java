package com.skeeter.birds;

import com.skeeter.game.Bird;

import java.util.Random;

/**
 * Created by fc53 on 08/10/16.
 */
public class Eagle extends Bird {

    Random randomGenerator = new Random();

    public void main(){
        positionX = 810;
        positionY = 10 + randomGenerator.nextInt(100);
        HitPoints = 1;
        VelocityX = 10 + randomGenerator.nextInt(20);
        VelocityY = 1 + randomGenerator.nextInt(9);
    }

    @Override
    public void update(long deltaT){
        positionX -= velocityX;
        positionY += velocityY;
        velocityY -= 1;
        if(positionY > 150){
            velocityY = 1 + randomGenerator.nextInt(9);
        }

    }
}
