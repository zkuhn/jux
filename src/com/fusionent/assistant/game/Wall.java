package com.fusionent.assistant.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.fusionent.assistant.ui.Animatable;
import com.fusionent.assistant.ui.AnimationCanvas;

public class Wall implements Animatable{
    
    public Point startPoint, endPoint;
    protected boolean highlighted = false;
    
    public Wall(Point start, Point end) {
        this.startPoint = start;
        this.endPoint = end;
    }
    
    
    public void draw(Graphics g){
        if(this.highlighted){
            g.setColor(Color.RED);
        }
        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        g.setColor(Color.BLACK);
    }

    @Override
    public void step(AnimationCanvas ac) {
        // TODO Auto-generated method stub
        
    }
    public void setHighlighted(boolean highlighted){
        this.highlighted = highlighted;
    } 
    
    public boolean horizontal(){
        return endPoint.y == startPoint.y;
    }
}
