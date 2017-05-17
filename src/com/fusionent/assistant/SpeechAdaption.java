package com.fusionent.assistant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class SpeechAdaption implements Runnable{

	public static final String PLEASE_READ = "Please read the following aloud while we record you.";
	public static final String PRESS_ENTER = "Press q to quit recording now. Press any other key to begin recording, then any key "
			                                 + "again when you are done, to stop recording."; 
	
	public TargetDataLine tdl;
	/**
	 * 
	 */
	public void doIt() {
		// first open some file with a list of lines of text that the speaker should read
		// Open the file  
		
		
		try {
			FileInputStream fstream = new FileInputStream("data\\test.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			
//			 loop through each line
//			 show the line
//			 ask the user to : hit record - speak the sentence - hit stop
//			 play back the recording to the user
//			 confirm the recording is good
//		     store the audio in a file with name corresponding to the sentence line (see tutorial format
//		     ask if they are done or would like to continue

			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
			  // Print the content on the console
				System.out.println(PLEASE_READ);
				System.out.println (strLine);
				  
				boolean recorded = this.recordMessage();
				if(!recorded) {
				System.out.println ("Quitting recording now.");
				break;
				}
				//may need to do data conversion here if we didn't record in compatible format.
				System.out.println(PLEASE_READ);
			  
			}

			//Close the input stream
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		// when they are done by choice or end of file, send the audio to the CMU adapter process to create the adaption
		
		
	}
	
	public boolean recordMessage () {
		System.out.println(PRESS_ENTER);
		try {
			int  b = System.in.read();
			if (b == 'q') {
				return false;
			}
			
			//let this run in a second thread while we block for stop input on this thread
			this.startRecording();
			
			//careful, windowns may pick up \n\r instead of just \n
			b = System.in.read();

			  
			//stop recording
			System.out.println("recording");
			b = System.in.read();
			System.out.println("got second input:" + b);
			this.stopRecording();
			
			
			
			
			System.out.println("done recording");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public void startRecording() throws IOException, LineUnavailableException {
		//record the message in a new thread
		
		
		//int numBytesRead;
		//byte[] data = new byte[l.getBufferSize() / 5];
		(new Thread(this)).start();

		
	}
	
	public void stopRecording() {
		tdl.stop();
		tdl.close();
	}

	@Override
	public void run() {
		try {
			MicrophoneListener mi = new MicrophoneListener();
			tdl = mi.getMicrophoneLine();
			if(tdl == null) {
				System.out.println("Couldn't find your mic. Maybe it's not plugged in?");
			}
			tdl.open();
			//ByteArrayOutputStream out  = new ByteArrayOutputStream();
			File saveFile = new File("audioTest.wav");
// Begin audio capture.
			tdl.start();
			
			AudioSystem.write(
					new AudioInputStream(tdl),
					AudioFileFormat.Type.WAVE,
					saveFile);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
