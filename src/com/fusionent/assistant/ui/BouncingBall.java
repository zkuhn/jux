package com.fusionent.assistant.ui;

import java.awt.Color;
import java.awt.Graphics;

public class BouncingBall implements Animatable {
    
    float x, y;
    int radius;
    float i, j;
    
    protected Color ballColor = Color.BLACK;
   
    
    public BouncingBall(int x , int y){
        this.x = x;
        this.y = y;
        this.radius = 20;
        this.i = 1;
        this.j = 1;
        
    }
    
    public void draw(Graphics g) {
        Color popColor = g.getColor();
        g.setColor(this.ballColor);
        g.fillArc(Math.round(this.x), Math.round(this.y), this.radius, this.radius, 0, 360);
       
        g.setColor(popColor);
    }
    
    /**
     * Step within the confines of the animation canvas
     */
    public void step(AnimationCanvas ac){
        this.x += this.i;
        this.y += this.j;
        
        //randomly change velocity slightly about once every thousand steps.
        if (Math.random() > .995){
            this.i += (Math.random() - .5);
            this.j += (Math.random() - .5);
           
            this.alterColor();
        }
        
        //bounce
        if(x > ac.getWidth() - radius || x <= 0) {
            this.i = -1 * this.i;
            
            
        }
        if(y > ac.getHeight() - radius || y<= 0) {
            this.j = -1 * this.j;
        }
        
        //System.out.println("x=" + x +"y=" + y);
    }
    
    protected void alterColor(){
        int blue = this.ballColor.getBlue();
        int red = this.ballColor.getRed();
        int green = this.ballColor.getGreen();
        
        if (Math.random() > .75){
            red += 35;
        }
        if (Math.random() > .75){
            blue += 35;
        }
        if (Math.random() > .75){
            green += 35;
        }
        red = red % 255;
        blue = blue % 255;
        green = green % 255;
        
        this.ballColor = new Color(red, blue, green);
        
    }

}
