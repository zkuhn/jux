/**
 * 
 */
package com.fusionent.assistant;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import com.fusionent.assistant.ui.AdaptionControlPanel;
import com.fusionent.assistant.ui.MainMenuController;

/**
 * @author zkuhn
 *
 */
public class Assistant {

	public static String TITLE = "Assistant 0.1";
	MicrophoneListener listener = new MicrophoneListener();
	
	int stopCount = 0;
	protected JFrame mainPanel;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Assistant mainHelper = new Assistant();
		//SpeechAdaption sa = new SpeechAdaption();
		//sa.doIt();
		
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		mainHelper.showUI();
		//mainHelper.startAudio();
		
		//mainHelper.monitorEmail();
		//mainHelper.showAudio();
		Transcriber t = new Transcriber();
		try {
			//t.transcribe(mainHelper);
			System.out.println("done transcribing");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void interpret(String interpreted) {
		if(interpreted.equals("close")) {
			stopCount++;
			if ( stopCount > 2) {
				System.exit(0);
			}
		}
		if(interpreted.equals("zero")) {
			// play Audio: yes self, I am listening
			this.playVoice("zeroVoice.wav");
		}
		
		if(interpreted.equals("hello")) {
			// play Audio: yes self, I am listening
			this.playVoice("zeroHello.wav");
		}
		if(interpreted.equals("sad")) {
			// play Audio: yes self, I am listening
			this.playVoice("zeroSad.wav");
		}
		if(interpreted.equals("reminder")) {
			// play Audio: yes self, I am listening
			this.playVoice("zeroReminder.wav");
		}
		
	}
	
	public void playVoice(String file) {
		
		
		 
		 try {
			 Clip clip = AudioSystem.getClip();
			 System.out.println("clip is" + clip);
			 System.out.println(Assistant.class.getResourceAsStream(file));
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
	        		new File(file)
            );
	        System.out.println("input stream is" + inputStream);
	        clip.open(inputStream);
	        clip.start(); 
	      } catch (Exception e) {
	    	  System.out.println("Exception thrown playing voice");
	    	  System.out.println(e);
	        System.err.println(e.getMessage());
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
	
	public void startWebcam(){
		WebcamGrabber cam = new WebcamGrabber();
		Thread t = new Thread(cam);
		t.start();
		
	}

	public void showUI() {
	    
		mainPanel = new JFrame();
		mainPanel.setTitle(Assistant.TITLE);
		mainPanel.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
		        //This will only be seen on standard output.
				shutdown();
		    }
		});
		
		//TODO where should this really go - fix
		AdaptionController controller;
        try {
            controller = this.buildAdaptionController();
            mainPanel.add( controller.getControlPanel() );
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
		
		//mainPanel.setSize(200, 150);
        MainMenuController mainMenuController = new MainMenuController(this);
        mainPanel.setJMenuBar(mainMenuController.getMenuBar());
        
		mainPanel.pack();
		mainPanel.setVisible(true);
	}
	
	public AdaptionController buildAdaptionController() throws Exception{
	    
	    AdaptionController controller = new AdaptionController(new SpeechAdaption()); 
	    AdaptionControlPanel acp = new AdaptionControlPanel( controller );
	    controller.setControlPanel(acp);
	    return controller;
	    
	}

    public void shutdown() {
        // TODO Auto-generated method stub
        listener.stopRecording();
        System.exit(0);
    }

}
