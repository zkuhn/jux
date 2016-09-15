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
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Assistant mainHelper = new Assistant();
		mainHelper.showUI();
		mainHelper.startAudio();
		mainHelper.monitorEmail();

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

	public void showUI() {
		JFrame mainPanel = new JFrame();
		mainPanel.setTitle(Assistant.TITLE);
		mainPanel.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
		        //This will only be seen on standard output.
		        System.exit(0);
		    }
		});
		mainPanel.setSize(200, 150);
		mainPanel.setVisible(true);
	}

}
