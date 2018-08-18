package com.fusionent.assistant.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.fusionent.assistant.game.Wall;

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
        Point center = this.getCenterPoint();
        g.fillArc(Math.round(this.x), Math.round(this.y), this.radius*2, this.radius*2, 0, 360);
       
        g.setColor(popColor);
        
        g.setColor(Color.BLUE);
        g.drawRect(center.x, center.y, 2, 2);
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
        
        //bounce on outside
        if(x > ac.getWidth() - radius || x <= 0) {
            this.i = -1 * this.i;
            
            
        }
        if(y > ac.getHeight() - radius || y<= 0) {
            this.j = -1 * this.j;
        }
        
        for(Wall wall: ac.getWalls()){
            if(this.intersects(wall) ) {
                //let's bounce both ways
                wall.setHighlighted(true);
                if(wall.horizontal()){
                    this.j = -1 * this.j;
                } else{
                    this.i = -1 * this.i;
                }
                break;
            } 
//            wall.setHighlighted(false);
        }
        
        //System.out.println("x=" + x +"y=" + y);
    }
    
    public Point getCenterPoint(){
        return new Point(Math.round(x) + radius, Math.round(y) +radius);
    }
    
    public getSlope(Point p1, Point p2){
        
    }
    
    public boolean intersects(Wall w) {
        Point center = this.getCenterPoint();
        
        
        
        
        //all wrong (mostly)
        
        /*if(!this.isNear(w)){
            w.setHighlighted(false);
            return false;
            //w.setHighlighted(true);
        } */
        
//        w.setHighlighted(true);
        //special cases
        //vertical wall
        if(w.startPoint.x == w.endPoint.x) {
            float distance = Math.abs(center.x - w.startPoint.x);
            
            if( distance> radius){
                return false;
                
            }
            
            int bottomY = w.startPoint.y < w.endPoint.y ?w.startPoint.y: w.endPoint.y;
            int topY = w.startPoint.y > w.endPoint.y ?w.startPoint.y: w.endPoint.y;
            
            if ( bottomY > center.y + radius) {
                return false;
            }
            if ( topY < center.y - radius) {
                return false;
            }
            System.out.println("centerx:" + center.x + "distance" + distance + "w.startPoint.x" + w.startPoint.x);
            System.out.println("boty" + bottomY + ":centerY" + center.y + ":topY" + topY);
            return true;
        } else {
            if(Math.abs(center.y - w.startPoint.y) > radius){
                return false;
            }
        //if(w.startPoint.x == w.endPoint.x) {
            int lesser = w.startPoint.x < w.endPoint.x ?w.startPoint.x: w.endPoint.x;
            int greater = w.startPoint.x > w.endPoint.x ?w.startPoint.x: w.endPoint.x;
            
            if (  center.x + radius < lesser ) {
                return false;
            }
            if ( greater < center.x - radius) {
                return false;
            }
            System.out.println("centery:" + center.y + "distance" + Math.abs(center.y - w.startPoint.y) + "w.startPoint.y" + w.startPoint.y);
            System.out.println("botx" + lesser + ":centerx" + center.x + ":topx" + greater);
            return true;
        }
        
        /*    
            if ( bottomY < this.y  && this.y < topY) {
                if (Math.abs(this.x - w.startPoint.x) < this.radius){
                    return true;
                } else {
                    return false; //definitively NOT intersecting
                }
            }
            System.out.println("Near, but not directly next to");
        }
         
         //if the radius is less than that distance, we've touched the wall(false).
        double distance = lineToPointDistance(
                new Point(Math.round(this.x + radius), Math.round(this.y + radius)), 
                w.startPoint, 
                w.endPoint );
         
         if( Math.abs(distance) < this.radius){
             w.setHighlighted(true);
             System.out.println("distance is" + distance);
             return true;
         }
         return false;
         */
    }
    
    public double lineToPointDistance(Point p1, Point line1, Point line2 ){
      //use this formula to calc the distance between a line and a point.
        float numerator = (line2.y - line1.y) * p1.x  - 
                (line2.x - line1.x) * p1.y + 
                line2.x * line1.y - 
                line2.y * line1.x;
        
        double denominator = Math.sqrt((line2.y - line1.y) ^2 +(line2.x - line1.x) ^2);
        
        double distance = numerator/denominator;
        return distance;
    }
    
    protected int distance(int x1, float x2){
        return (Math.abs(x1 - Math.round(x2)) );
    }
    public boolean isNear(Wall w) {
        int diameter = this.radius*2;
        Point center = this.getCenterPoint();
        if ( distance(w.endPoint.x, center.x) < diameter &&
             distance(w.endPoint.y, center.y) < diameter){
            return true;
        }
        if ( distance(w.startPoint.x, center.x) < diameter &&
             distance(w.startPoint.y, center.y) < diameter){
               return true;
        }
        
        
        return false;
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
