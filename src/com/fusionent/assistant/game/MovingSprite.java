package com.fusionent.assistant.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.util.Vector;

import com.fusionent.assistant.ui.Animatable;
import com.fusionent.assistant.ui.AnimationCanvas;

public class MovingSprite extends Sprite {

    PositionDriver positionDriver;

    public MovingSprite(Vector<Image> animations) {
        super(animations);
        // TODO Auto-generated constructor stub
        this.positionDriver = new PositionDriver(new Point2D.Float(0, 0), new Point2D.Float(1.0f, 1.0f)); 
    }
    /**
     * 
     * @param ac
     */
    public void step(AnimationCanvas ac){
        super.step(ac);
        this.positionDriver.step(ac);
    }

    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        g.drawImage(animationImages.elementAt(loopPosition), positionDriver.getIntX(), positionDriver.getIntY(), null);
        
    }
}
