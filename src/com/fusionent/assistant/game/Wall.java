package com.fusionent.assistant.game;

import java.awt.Graphics;
import java.awt.Point;

import com.fusionent.assistant.ui.Animatable;
import com.fusionent.assistant.ui.AnimationCanvas;

public class Wall implements Animatable{
    Point startPoint;
    Point endPoint;
    public Wall(Point start, Point end) {
        this.startPoint = start;
        this.endPoint = end;
    }
    
    
    public void draw(Graphics g){
        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }

    @Override
    public void step(AnimationCanvas ac) {
        // TODO Auto-generated method stub
        
    }
}
