package com.fusionent.network;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.BoxLayout;

public class NetworkMonitor extends Frame implements ActionListener, Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2423123477251048065L;
	
	private static final String windowTitle = "Network Monitor";
	
	private Vector<HostAvailabilityMonitor> hostMonitors = new Vector<HostAvailabilityMonitor>(); 
	
	TextField ipbox;
	boolean running = false;
	GraphPanel gp;
	

	public static void main(String[] args) {
		
		NetworkMonitor main = new NetworkMonitor();
		main.initialize();
		
		PingTest pt = new PingTest();
		pt.pingSuiteTest();
		
		
	}
	
	public void initialize() {
		this.setSize(400,300);
		this.setVisible(true);
		this.setTitle(windowTitle);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		
		
		this.setLayout(new BorderLayout(2,2));
		
		Panel inputPanel = getHostAddPanel();
		
		this.add(inputPanel, BorderLayout.WEST);
		
		gp = new GraphPanel();
		gp.setPreferredSize(new Dimension(420,420));
		this.add(gp, BorderLayout.CENTER);
		
		this.monitorHost("192.168.1.1");
        this.monitorHost("192.168.1.2");
        //this.monitorHost("127.0.0.1");
        
        //this.monitorHost("192.168.2.1");
        //this.monitorHost("216.58.216.14");
		
		this.pack();
		this.setVisible(true);
		this.startGraphing();
		
		
	}

	/**
	 * A simple panel with a text input for the dot quad address, and an add button with this class set as listener
	 * 
	 * @return
	 */
    protected Panel getHostAddPanel() {
        Panel inputPanel = new Panel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));
		
		ipbox = new TextField();
		ipbox.setColumns(15);
		inputPanel.add(ipbox);
		
		Button addButton = new Button("Add");
		addButton.addActionListener(this);
		inputPanel.add(addButton);
		
		inputPanel.setPreferredSize(new Dimension(150,40));
        return inputPanel;
    }
	
	public void actionPerformed(ActionEvent e) { 
	     System.out.println(ipbox.getText());
	     String ip = ipbox.getText();
	     this.monitorHost(ip);
	}
	
	public void monitorHost(String hostAddress) {
		
		HostAvailabilityMonitor hostMonitor = new HostAvailabilityMonitor(hostAddress);		
		gp.setMonitorTitle(hostAddress);
		hostMonitors.add(hostMonitor);
		(new Thread(hostMonitor)).start();
		
	}
	
	public void startGraphing() {
		(new Thread(this)).start();
	}
	
	public void run(){
		this.running = true;
		// infinite loop until outside force calls class's stop method
		while(this.running) {
			try {
				Thread.sleep(HostAvailabilityMonitor.DEFAULT_INTERVAL);
				this.updateGraph();
			} catch (InterruptedException e) {
				// for now, no other threads should be waking us up, so semi-ignore this
				e.printStackTrace();
			}
		}
	}
	
	public void updateGraph() {
		for(HostAvailabilityMonitor hostMonitor : hostMonitors ) {
			gp.setNextValue(hostMonitor.pingTimes.lastElement());
		}
	}
	
}
