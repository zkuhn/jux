package com.fusionent.assistant;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Questioner implements ActionListener{
	JTextArea responseArea;
	java.awt.Dialog prompter;

	public void askQuestion(Frame f) {
		
		prompter = new java.awt.Dialog(f, "Test 123", false);
		
		
		JButton submit  = new JButton("Submit");
		submit.addActionListener(this);
		
		
		JTextField jta = new JTextField("What do you want to do?");
		
		prompter.add(jta, BorderLayout.NORTH);
		
		responseArea = new JTextArea();
		
		prompter.add(responseArea, BorderLayout.CENTER);
		
		prompter.add(submit, BorderLayout.SOUTH);
		prompter.pack();
		
		prompter.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		System.out.println(responseArea.getText());
		prompter.setVisible(false);
	}
	
}
