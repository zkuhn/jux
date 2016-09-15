package com.fusionent.assistant;

import javax.sound.sampled.*;

public class MicrophoneListener {

	public void open() {
		TargetDataLine line;
		AudioFormat format = new AudioFormat(44100, 16, 2, true, true);
		
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, 
		    format); // format is an AudioFormat object
		if (!AudioSystem.isLineSupported(info)) {
		    // Handle the error ... 

		}
		// Obtain and open the line.
		try {
			
			
		    line = (TargetDataLine) AudioSystem.getLine(info);
		    line.open(format);
		    System.out.println("Ready to listen");
		} catch (LineUnavailableException ex) {
		    // Handle the error ... 
		}
	}
}
