package com.fusionent.assistant.game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;

import com.fusionent.assistant.ui.Animatable;
import com.fusionent.assistant.ui.AnimationCanvas;

public class Sprite implements Animatable{

    Vector<Image> animationImages;
    int loopPosition = 0;
    
    public Sprite (Vector<Image> animations) {
        this.animationImages = animations;
    }
    
    @Override
    public void step(AnimationCanvas ac) {
        // TODO Auto-generated method stub
        loopPosition++;
        
        loopPosition = loopPosition % animationImages.size();
        
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        g.drawImage(animationImages.elementAt(loopPosition), 0, 0, null);
        
    }

}
