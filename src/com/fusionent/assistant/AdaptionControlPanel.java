package com.fusionent.assistant;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdaptionControlPanel extends Panel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2171950437835229791L;
	
	protected Button recordButton;
	protected Label  actionMessage;
	protected Label  textMessage;
	
	protected AdaptionController controller;
	
	protected boolean recording = false;
	
	public AdaptionControlPanel(AdaptionController controller) {
		
		recordButton  = new Button("Start");
		actionMessage = new Label("Press Start to begin recording");
		textMessage   = new Label("Text not loaded yet");
		this.controller = controller;
		recordButton.addActionListener(this);
		this.add(recordButton);
		this.add(actionMessage);
		this.add(textMessage);
		this.setPreferredSize(new Dimension(200,150));
		this.setVisible(true);
		
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		this.recording = !this.recording;
		if(recording) {
			controller.processStartAction();
			actionMessage.setText("Recording text");
			recordButton.setLabel("Stop");
		} else {
			controller.processStopAction();
			actionMessage.setText("Ready for next message");
			recordButton.setLabel("Start");
		}
	}

}
