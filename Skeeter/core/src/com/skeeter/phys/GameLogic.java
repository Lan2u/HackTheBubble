package com.skeeter.phys;

import com.skeeter.birds.Bird;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Created by fc53 on 08/10/16.
 */

public class GameLogic{

    private static final int FRAME_WIDTH = 800;

    public static boolean bShot(Point gunPoint, Bird bird, double barrelAngle){
        Line2D line = new Line2D.Double();
        double m = Math.tan(barrelAngle);
        System.out.println(m);
        line.setLine(gunPoint, new Point(FRAME_WIDTH,(int) (FRAME_WIDTH * m)));

        return bird.intersectsLine(line);
    }

    //Checks if a bird object is off the screen
    public static boolean birdOffScreen(Bird bird){
        if(bird.getX() + bird.getWidth() < 0){
            return true;
        }else{
            return false;
        }
    }

    //Checks if an ostrich has hit the shooter ( has an x value less that the shooter)
    public static boolean hitByOstrich(Bird bird){
        if(bird.getX() < 100){
            return true;
        }
        else{
            return false;
        }
    }
}
