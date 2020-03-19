package com.fusionent.assistant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

public class SentenceLoader {

	Vector<String> sentences = new Vector<String>();
	
	/**
	 * Load a file and assume there is once sentence per line.
	 * @param fileName
	 */
	public SentenceLoader(String fileName){
		
		File f = new File(fileName);
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line;
			while( (line = br.readLine()) != null){
				sentences.add(line);
			}
			br.close();
		}catch(Exception e) {
			
		}
	}
	
	public Vector<String> getSentences() {
		return this.sentences;
	}
}
