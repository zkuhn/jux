package com.fusionent.network;
import java.io.IOException;
import java.util.*;

public class HostAvailabilityMonitor implements Runnable{
	
	public static final long DEFAULT_INTERVAL = 500;
	
	protected Vector<Long>          pingTimes      = new Vector<Long>();
	protected Vector<MonitorListener> eventListeners = new Vector<MonitorListener>();
	
	PingTest p = new PingTest();
	String hostAddress;
	
	private boolean running = false;
	
	public HostAvailabilityMonitor (String address) {
		this.hostAddress = address;
	}
	
	public void stop() {
		this.running = false;
	}
	
	public void addMonitorListener(MonitorListener ml) {
		this.eventListeners.add(ml);
	}
	
	public void run() {
		this.running = true;
		// infinite loop until outside force calls class's stop method
		while(this.running) {
			this.monitor(this.hostAddress);
			try {
				Thread.sleep(HostAvailabilityMonitor.DEFAULT_INTERVAL);
			} catch (InterruptedException e) {
				// for now, no other threads should be waking us up, so semi-ignore this
				e.printStackTrace();
			}
		}
	}
	
	public void monitor(String address) {
		
		long pingTime = 0;
		try {
			pingTime = p.timedPing(address, PingTest.DEFAULT_TIMEOUT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pingTimes.add(new Long(pingTime));
		this.monitorNotice(pingTime);
		this.notifyListeners(pingTime);
		
	}
	
	protected void notifyListeners(long pingTime) {
		for (MonitorListener toNotify : eventListeners) {
			toNotify.processPing(pingTime);
		}
	}
	
	public void monitorNotice(long pingTime) {
		System.out.println(this.hostAddress + " is reachable in "  + pingTime/1000000 + " milliseconds");
	}
	
	public void discover() throws Exception{
		String ipAddress = "76.176.109.161";
		boolean success = p.ping(ipAddress, PingTest.DEFAULT_TIMEOUT);
		// strategy is to ping the broadcast address, see if a bunch of stuff comes back
		// next step is to traceroute to google, 
		// after that, see what we can see 
		
		
	}

}
