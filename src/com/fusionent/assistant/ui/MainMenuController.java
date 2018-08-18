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
        
        this.addMenuItem("Start Webcam", new ActionListener(){    
            public void actionPerformed(ActionEvent ae) {
                assistant.startWebcam();
            }
        });

        this.addMenuItem("monitorEmail", new ActionListener(){    
            public void actionPerformed(ActionEvent ae) {
                assistant.monitorEmail();
            }
        });
        
        this.addMenuItem("showAudio", new ActionListener(){    
            public void actionPerformed(ActionEvent ae) {
                System.out.println("audio clicked");
                assistant.showAudio();
            }
        });
        
        this.addMenuItem("transcribe", new ActionListener(){    
            public void actionPerformed(ActionEvent ae) {
                assistant.transcribe();
            }
        });
        
        this.addMenuItem("animation", new ActionListener(){    
            public void actionPerformed(ActionEvent ae) {
                assistant.animate();
            }
        });
        
        this.addMenuItem("Test Page Fetch", new ActionListener(){    
            public void actionPerformed(ActionEvent ae) {
                assistant.testPageFetch();
            }
        });
        
        this.addMenuItem("Exit", new ActionListener(){    
            public void actionPerformed(ActionEvent ae) {
                assistant.shutdown();
            }
        });
        

        
        menuBar.add(mainMenu);
        
    }
    
    protected void addMenuItem(String menuItemName, ActionListener action) {
        JMenuItem newMenuItem = new JMenuItem(menuItemName);
        newMenuItem.addActionListener(action);
        mainMenu.add(newMenuItem);
    }
    
    public JMenuBar getMenuBar(){
        return this.menuBar;
    }

}
