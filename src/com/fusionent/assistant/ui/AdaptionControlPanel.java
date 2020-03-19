package com.fusionent.assistant.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JLabel;

import com.fusionent.assistant.AdaptionController;

public class AdaptionControlPanel extends Panel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2171950437835229791L;
	
	protected Button recordButton;
	protected Label  actionMessage;
	protected JLabel  textMessage;
	
	protected AdaptionController controller;
	
	protected Vector<String> sentences;
	Iterator<String> sentenceIterator;
	
	protected boolean recording = false;
	
	public AdaptionControlPanel(AdaptionController controller) {
		
		recordButton  = new Button("Start");
		actionMessage = new Label("Press Start to begin recording");
		textMessage   = new JLabel("Text not loaded yet");
		
		
		this.controller = controller;
		recordButton.addActionListener(this);
		this.add(recordButton);
		this.add(actionMessage);
		this.add(textMessage);
		this.setPreferredSize(new Dimension(200,150));
		this.setVisible(true);
		
		sentences = controller.getSentences();
		sentenceIterator = sentences.iterator();
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		this.recording = !this.recording;
		if(recording) {
			controller.processStartAction();
		} else {
			controller.processStopAction();
			
		}
	}
	
	public void updateToRecording() {
	    actionMessage.setText("Recording text");
        recordButton.setLabel("Stop");
        String sentence = sentenceIterator.next();
        if(sentence == null) {
        	sentence = "No further sentences found.";
        }
        System.out.println("Length1 = " + sentence.length() + " : " + sentence);
        
        while(sentence.length() == 0) {
        	sentence = sentenceIterator.next();
        }
        System.out.println("Length2 = " + sentence.length() + " : " + sentence);
        textMessage.setText(" - " + sentence + " - ");
	}
	
	public void updateToWaiting() {
	    actionMessage.setText("Ready for next message");
        recordButton.setLabel("Start");
	}

    public void setNextMessage(String nextMessage) {
        // TODO Auto-generated method stub
        textMessage.setText(nextMessage);
    }

}
