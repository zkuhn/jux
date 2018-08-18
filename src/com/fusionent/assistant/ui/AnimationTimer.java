package com.fusionent.assistant.ui;


import java.util.Vector;

import javax.swing.JPanel;

public class AnimationTimer extends Thread{

    
    protected Vector<Animatable> animations = new Vector<Animatable>();
    protected AnimationCanvas animationCanvas;
    
    protected int lastTick;
    
    public AnimationTimer(AnimationCanvas animationCanvas) {
        this.animationCanvas = animationCanvas;
    }
    public void addAnimatable(Animatable a) {
        this.animations.addElement(a);
    } 
    public Vector<Animatable> getAnimations(){
        return this.animations;
    }
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        
        while(true) {
            try {
                Thread.sleep(25);
                //System.out.println("tick");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } //about 60fps
            
            for(Animatable a : animations) {
                //System.out.println("tock");
                a.step(animationCanvas);
                
            }
            
            animationCanvas.repaint();
        }
        
    }
    
    
    

}
