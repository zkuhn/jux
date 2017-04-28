package com.fusionent.assistant;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.AbstractSpeechRecognizer;
import edu.cmu.sphinx.result.WordResult;

public class Transcriber {
	
  public void transcribe(Assistant assistant) throws Exception {
      
        Configuration configuration = new Configuration();

        configuration
                .setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration
                .setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration
                .setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

        //AbstractSpeechRecognizer recognizer = this.transcribeFromFile(configuration);
        AbstractSpeechRecognizer recognizer = this.transcribeFromMic(configuration);
        
        
        
        SpeechResult result;
        while ((result = recognizer.getResult()) != null) {
        	
        	for(WordResult resultWord : result.getWords()){
        		System.out.println(resultWord);
        	}
        	String interpretedWord = result.getHypothesis();
        	assistant.interpret(interpretedWord);
            System.out.format("Hypothesis: %s\n", interpretedWord);
            
            
        }
        //recognizer.stopRecognition();
    }
	  
  public AbstractSpeechRecognizer transcribeFromFile(Configuration configuration) throws Exception {
	  StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(
              configuration);
      InputStream stream = new FileInputStream(new File("audioTest3.wav"));

      recognizer.startRecognition(stream);
      return recognizer;
  }
  
  public AbstractSpeechRecognizer transcribeFromMic(Configuration configuration)  throws Exception{
	  LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
	  // Start recognition process pruning previously cached data.
	  recognizer.startRecognition(true);
	  
	  return recognizer;
  }

}
