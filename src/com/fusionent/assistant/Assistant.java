/**
 * 
 */
package com.fusionent.assistant;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

/**
 * @author zkuhn
 *
 */
public class Assistant {

	public static String TITLE = "Assistant 0.1";
	MicrophoneListener listener = new MicrophoneListener();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Assistant mainHelper = new Assistant();
		mainHelper.showUI();
		//mainHelper.startAudio();
		mainHelper.startWebcam();
		//mainHelper.monitorEmail();
		//mainHelper.showAudio();
		Transcriber t = new Transcriber();
		try {
			t.transcribe();
			System.out.println("done transcribing");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void monitorEmail() {
		// TODO Auto-generated method stub
		EmailReview reviewer = new EmailReview();
		reviewer.monitorImapMailbox();
		
	}
	
	protected void startAudio() {
		MicrophoneListener listener = new MicrophoneListener();
		listener.open();
	}
	
	protected void showAudio() {
		
		//listener.displayMixers();
		listener.showMicLine();
	}
	
	protected void startWebcam(){
		WebcamGrabber cam = new WebcamGrabber();
		Thread t = new Thread(cam);
		t.start();
		
	}

	public void showUI() {
		JFrame mainPanel = new JFrame();
		mainPanel.setTitle(Assistant.TITLE);
		mainPanel.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
		        //This will only be seen on standard output.
				listener.stopRecording();
		        System.exit(0);
		    }
		});
		mainPanel.setSize(200, 150);
		mainPanel.setVisible(true);
	}

}
