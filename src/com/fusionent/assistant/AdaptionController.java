package com.fusionent.assistant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

import com.fusionent.assistant.ui.AdaptionControlPanel;

public class AdaptionController implements ActionListener{

    protected SpeechAdaption sa;
    protected AdaptionControlPanel acp;
    protected int adaptionLine = 1;
    
    public AdaptionController(SpeechAdaption sa){
        this.sa = sa;
    }
    
	public void processStartAction(){
	    acp.updateToRecording();
		try {
            sa.startRecording();
        } catch (IOException | LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	public void processStopAction(){
	    acp.updateToWaiting();
		sa.stopRecording();
		
		try {
            acp.setNextMessage(sa.getNextMessage());
            adaptionLine++; //once the next line is successfully fetched, increment the adaption line count (so we can record files)
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	public void actionPerformed(ActionEvent ae) {
		
	}

	public void setControlPanel(AdaptionControlPanel acp) throws IOException {
	    this.acp = acp;
	    acp.setNextMessage(sa.getNextMessage());
	}
	
    public AdaptionControlPanel getControlPanel() {
        // TODO Auto-generated method stub
        return this.acp;
    }
	
	
}
