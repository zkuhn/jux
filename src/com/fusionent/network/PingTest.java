package com.fusionent.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PingTest {
	
	public static final int DEFAULT_TIMEOUT = 5000; 

	public void pingSuiteTest() {
		this.runPingTest("127.0.0.1");
		this.runPingTest("192.168.1.1");
		this.runPingTest("192.168.2.1");
		
		
		//this.runPingTest("173.194.32.38");
	}
	
	public void runPingTest(String ipAddress ) {
		try { 
		    System.out.println("Sending Ping Request to " + ipAddress);
		    if(this.ping(ipAddress, DEFAULT_TIMEOUT)) {
		    	System.out.println("Host is reachable in "  + this.timedPing(ipAddress, DEFAULT_TIMEOUT)/1000000 + " milliseconds");
		    } else {
		    	System.out.println("Host is NOT reachable");
		    }
		    
		} catch(UnknownHostException uhe) {
			System.out.println(uhe);
		} catch(IOException ioe) {
			System.out.println(ioe);
		}
	}
	
	/**
	 * Do an untimed ping and return if we could reach the address or not. This is assuming an IPv4 address. Untested for IPv6
	 * 
	 * @param ipAddress
	 * @param timeout
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public boolean ping(String ipAddress, int timeout) throws UnknownHostException, IOException {
		InetAddress inet = InetAddress.getByName(ipAddress);
		return inet.isReachable(timeout);
	}
	
	/**
	 * Call this class's ping method, and time it in nanos. return a positive nano count for success or -1 on fail.
	 * 
	 * @param ipAddress
	 * @param timeout
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public long timedPing(String ipAddress, int timeout) throws UnknownHostException, IOException {
		long time = System.nanoTime();
		if (this.ping(ipAddress, timeout) ) {
			return System.nanoTime() - time; 
		}
		return -1;
	}
	

}
