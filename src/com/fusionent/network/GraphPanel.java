package com.fusionent.network;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GraphPanel extends JPanel{

	 /**
	 * 
	 */
	private static final long serialVersionUID = -591115104615385014L;
	private int progress = 0;
	private int oldValue = 0;
	private int value = 0;
	private static final int INCREMENT = 2;
	private static final int OFFSET    = 20;
	
	protected String monitorTitle;

	public void paintComponent(Graphics g) {
         //super.paintComponent(g);

         g.drawString(monitorTitle, 0, OFFSET);
         //g.drawRect(200, 200, 200, 200);
         g.setColor(Color.BLUE);
         g.drawLine(progress, oldValue + OFFSET, progress + INCREMENT, value + OFFSET);
         System.out.println("drawing " + progress  +", " + oldValue +", " + (progress + INCREMENT) + ", " + value);
         
    }
	
	public void setMonitorTitle(String monitorTitle) {
		this.monitorTitle = monitorTitle;
	}
	
	public void setNextValue(Long newValue){
		
		oldValue = value;
		value = newValue.intValue() /1000000;
		this.repaint();
		progress += INCREMENT;
	}
}
