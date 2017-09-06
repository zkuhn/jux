package com.fusionent.assistant.ui;

import java.awt.Graphics;

public interface Animatable {

    public void step(AnimationCanvas ac);
    public void draw(Graphics g);
}
