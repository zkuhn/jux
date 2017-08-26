package com.fusionent.assistant.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import com.fusionent.assistant.Assistant;

public class MainMenuController {

    /**
     * 
     */
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1431118600409868054L;
    
    JMenu    mainMenu;
    JMenuBar menuBar;
    Assistant assistant;
    
    public MainMenuController(Assistant assistant) {
        
        this.assistant = assistant;
        
      //Create the menu bar.
        menuBar = new JMenuBar();
        
        mainMenu = new JMenu("Controls");
        mainMenu.setMnemonic(KeyEvent.VK_C);
        
        JMenuItem startWebcam = new JMenuItem("Start Webcam");
        startWebcam.addActionListener( new ActionListener(){    
            public void actionPerformed(ActionEvent ae) {
                //This will only be seen on standard output.
                assistant.startWebcam();
            }
        });
        
        mainMenu.add(startWebcam);
        
        JMenuItem exitOption = new JMenuItem("Exit");
        exitOption.addActionListener( new ActionListener(){    
            public void actionPerformed(ActionEvent ae) {
                //This will only be seen on standard output.
                assistant.shutdown();
            }
        });
        mainMenu.add(exitOption);
        
        menuBar.add(mainMenu);
        
    }
    
    public JMenuBar getMenuBar(){
        return this.menuBar;
    }

}
