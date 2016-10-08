package com.skeeter.phys;

import com.skeeter.birds.Bird;

/**
 * Created by fc53 on 08/10/16.
 */

public class GameLogic{

    public static boolean birdShot(Bird bird, double barrelAngle){
        boolean collision = false;
        for(int i = 0; i < bird.getWidth(); i ++){
            for(int j = 0; i < bird.getHeight(); j++){
                if(((bird.getY() + j) / (bird.getX() + i)) == java.lang.Math.tan(barrelAngle)){
                    collision = true;
                }
            }
        }
        return collision;
    }

    public static boolean birdOffScreen(Bird bird){
        if(bird.getX() + bird.getWidth() < 0){
            return true;
        }else{
            return false;
        }
    }

    public static boolean hitByOstrich(Bird bird){
        if(bird.getX() < 100){
            return true;
        }
        else{
            return false;
        }
    }
}
