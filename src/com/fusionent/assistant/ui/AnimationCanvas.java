package com.fusionent.assistant.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.fusionent.assistant.game.Wall;


public class AnimationCanvas extends JPanel implements MouseListener{
    
    /**
     * 
     */
    private static final long serialVersionUID = 4257096502191991304L;
    
    BouncingBall b;
    AnimationTimer animLoop;
    
    int pressedX, pressedY;

    public AnimationCanvas() {
        super();
        this.setSize(new Dimension(250,350));
        this.setVisible(true);
        this.requestFocus();
        
        animLoop = new AnimationTimer(this);
        animLoop.start();
        this.addBouncingBall(55, 50);
        
        this.addMouseListener(this);
        
        
    }

    public void paint(Graphics g) {
        //g.drawString("Animate mother ", 20, 40);
        g.setColor(getBackground());
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.BLACK);
        
        for(Animatable animation : animLoop.getAnimations()) {
            animation.draw(g);
        }
    }
    
    public void addBouncingBall(int x, int y) {
        b = new BouncingBall(x,y);
        animLoop.addAnimatable(b);
    }
    
    
    public void mouseClicked (MouseEvent me) {
        
        addBouncingBall(me.getX(), me.getY());
        System.out.println("Adding Ball at" + me.getX() + " , " +  me.getY());
    }
    public void mouseReleased(MouseEvent e) {
        //detect drag events
        if(e.getX() != pressedX || e.getY() != pressedY) {
            this.addWall(new Point(pressedX, pressedY), new Point (e.getX(), e.getY()));
        }
        
        System.out.println(":MOUSE_RELEASED_EVENT:");
    }
    
    public void mousePressed(MouseEvent e) {
        this.pressedX = e.getX();
        this.pressedY = e.getY();
        System.out.println("----------------------------------\n:MOUSE_PRESSED_EVENT:");
    }
    
    public void mouseExited(MouseEvent e) {
        System.out.println(":MOUSE_EXITED_EVENT:");
    }
    
    public void mouseEntered(MouseEvent e) {
        System.out.println(":MOUSE_ENTER_EVENT:");
    }
    
    public void addWall(Point start, Point end){
        Wall w = new Wall(start, end);
        animLoop.addAnimatable(w);
    }

}