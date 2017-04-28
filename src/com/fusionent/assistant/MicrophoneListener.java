package com.fusionent.assistant;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.sound.sampled.*;

public class MicrophoneListener {
	
	TargetDataLine l;

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
	
	public void displayMixers() {
		try {
			Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
			for (Mixer.Info info : mixerInfos) {
				Mixer m = AudioSystem.getMixer(info);
				System.out.println("\n Mixer");
				System.out.println(m.toString());
				Line.Info[] lineInfos = m.getSourceLineInfo();
				for (Line.Info lineInfo : lineInfos) {
					System.out.println("\n Source Line");
					System.out.println(info.getName() + "---" + lineInfo);
					Line line = m.getLine(lineInfo);
					System.out.println("\t-----" + line);
				}
				lineInfos = m.getTargetLineInfo();
				for (Line.Info lineInfo : lineInfos) {
					System.out.println("\n Target Line");
					System.out.println(m + "---" + lineInfo);
					Line line = m.getLine(lineInfo);
					System.out.println("\t-----" + line);

				}
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public TargetDataLine getMicrophoneLine() {
		try {
			Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
			for (Mixer.Info info : mixerInfos) {
				Mixer m = AudioSystem.getMixer(info);
				
				Line.Info[] lineInfos = m.getTargetLineInfo();
				for (Line.Info lineInfo : lineInfos) {
				
					String lineString = 
						"interface TargetDataLine";// supporting 8 audio formats, and buffers of at least 32 bytes";
					//lineString = "MICROPHONE source port";
					if(lineInfo.toString().startsWith(lineString) ) {
						
						return (TargetDataLine) AudioSystem.getLine(lineInfo);
						//Line line = m.getLine(lineInfo);
						//return (TargetDataLine) line;
					}
				}
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	public void stopRecording(){
		l.stop();
	}
	
	public void showMicLine() {
		l = this.getMicrophoneLine();
		System.out.println("got microphone --- " + l);
		Line.Info lineInfo = l.getLineInfo(); 
		System.out.println(lineInfo);
		try{
			l.open( );
			l.start();
			
			//boolean stopped = false;
			
			System.out.println(l.getLineInfo().getLineClass());
			System.out.println(l.getFormat().toString());
			
			//ByteArrayOutputStream out  = new ByteArrayOutputStream();
			File saveFile = new File("audioTest.wav");
			
			//int numBytesRead;
			//byte[] data = new byte[l.getBufferSize() / 5];

			// Begin audio capture.
			l.start();
			
			AudioSystem.write(
					new AudioInputStream(l),
					AudioFileFormat.Type.WAVE,
					saveFile);

			// Here, stopped is a global boolean set by another thread.
			/*while (!stopped) {
			   // Read the next chunk of data from the TargetDataLine.
			   numBytesRead =  l.read(data, 0, data.length);
			   // Save this chunk of data.
			   saveFile.write(data, 0, numBytesRead);
			} */
			//saveFile.close();
			
			
			
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
