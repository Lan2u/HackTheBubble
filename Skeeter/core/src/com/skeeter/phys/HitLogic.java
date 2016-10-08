package com.skeeter.phys;

import com.skeeter.game.Bird;

/**
 * Created by fc53 on 08/10/16.
 */
/*
public class GameLogic{

    //Checks if a bird's hitbox is in the line of the gun.
    public static boolean birdShot(Bird bird, double barrelAngle){
        boolean collision = false;
        for(int i = 0; i < bird.getWidth; i ++){
            for(int j = 0; i < bird.getHeight; j++){
                if(((bird.getY + j) / (bird.getX + i)) == java.lang.Math.tan(barrelAngle)){
                    collision = true;
                }
            }
        }
        return collision;
    }

    //Checks if a bird object is off the screen
    public static boolean birdOffScreen(Bird bird){
        if(bird.getX + bird.getWidth < 0){
            return true;
        }else{
            return false;
        }
    }

    //Checks if an ostrich has hit the shooter ( has an x value less that the shooter)
    public static boolean hitByOstrich(Bird bird){
        if(bird.getX < 100){
            return true;
        }
        else{
            return false;
        }
    }
}
*/