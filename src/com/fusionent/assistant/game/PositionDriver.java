package com.fusionent.assistant.game;

import java.awt.geom.Point2D;

import com.fusionent.assistant.ui.AnimationCanvas;

/**
 * A movement driver for 2d animations. just moves something, then bounces it off a wall.
 * Tries to add a it of random direction shift
 * Uses float instead of int so velocities aren't limited to int fractional slopes (1/2 , 2/3, etc can be .1234)
 * 
 * @author zakuhn
 *
 */
public class PositionDriver {

    public Point2D.Float position;
    public Point2D.Float velocityVector;
    
    public PositionDriver(Point2D.Float position, Point2D.Float velocityVector) {
        this.position = position;
        this.velocityVector = velocityVector;
        
    }
    
    public void step(AnimationCanvas ac){
        
        position.x += velocityVector.x;
        position.y += velocityVector.y;
        
        //randomly change velocity slightly .
        randomVelocityShift();
        
        //bounce on outside
        //if(position.x > ac.getWidth() - radius || x <= 0) {
        if(position.x > ac.getWidth() || position.x <= 0) {
            velocityVector.x = -1 * velocityVector.x;
            position.x += 2 * velocityVector.x; // undo the passing of the barrier, and send it back the other direction a bit 
            
            
        }
        //if( velocityVector.y > ac.getHeight() - radius ||  velocityVector.y<= 0) {
        if( position.y > ac.getHeight() ||  position.y <= 0) {
            velocityVector.y = -1 * velocityVector.y;
            position.y += 2 * velocityVector.y;
        }
    }
    
    /**
     * give about a 1 in 200 chance of shifting velocity
     * This was added so things don't all seem to follow the same path.
     */
    public void randomVelocityShift() {
        if (Math.random() > .995){
            velocityVector.x += (Math.random() - .5);
            velocityVector.y += (Math.random() - .5); //note second call makes y change different than x component.
        }
    }

    public int getIntX() {
        // TODO Auto-generated method stub
        return Math.round(position.x);
    }
    
    public int getIntY() {
        // TODO Auto-generated method stub
        return Math.round(position.y);
    }
}
