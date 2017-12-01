package com.fusionent.assistant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SphinxAdaption {

	public void runAdaption() {
		String path = "c:\\lib\\sphinxbase\\bin\\Release\\x64\\";
		String dataPath = "C:/lib/sphinx_adaption_data/";
		String cmd = "sphinx_fe -argfile "+dataPath+"feat.params  -samprate 16000 -c "+dataPath+"arctic20.fileids  -di . -do . -ei wav -eo mfc -mswav yes ";
		System.out.println(path+cmd);
		return;
//		try {
//			Process process = Runtime.getRuntime().exec(path+cmd);
//			
//			 // Get input streams
//            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//
//            // Read command standard output
//            String s;
//            System.out.println("Standard output: ");
//            while ((s = stdInput.readLine()) != null) {
//                System.out.println(s);
//            }
//            
//            System.out.println("Standard error: ");
//            while ((s = stdError.readLine()) != null) {
//                System.out.println(s);
//            }
//            
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
