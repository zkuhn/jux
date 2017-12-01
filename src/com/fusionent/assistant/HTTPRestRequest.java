package com.fusionent.assistant;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.security.cert.X509Certificate;
import javax.net.ssl.*;

public class HTTPRestRequest {
	
	public void configureSSL(){
		 HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier()
	        {
	            public boolean verify(String hostname, SSLSession session)
	            {
	                // ip address of the service URL(like.23.28.244.244)
	                if (hostname.equals("23.21.226.246"))
	                    return true;
	                return false;
	            }
	        });
	}
	
	public void allowSelfSignedCert(){
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { 
		    new X509TrustManager() {     
		        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		        	java.security.cert.X509Certificate[] returnArray = new java.security.cert.X509Certificate[] {};
		            return returnArray ;
		        } 
		        public void checkClientTrusted( 
		            java.security.cert.X509Certificate[] certs, String authType) {
		            } 
		        public void checkServerTrusted( 
		            java.security.cert.X509Certificate[] certs, String authType) {
		        }
		    } 
		}; 

		// Install the all-trusting trust manager
		try {
		    SSLContext sc = SSLContext.getInstance("SSL"); 
		    sc.init(null, trustAllCerts, new java.security.SecureRandom()); 
		    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
		} 
		
		
	}

	/**
	 * Note: the service wants to connect via SSL but since the cert is self-signed, there is some work that needs to be done so that
	 * java will accept the cert as self-signed: See: 
	 * https://stackoverflow.com/questions/9210514/unable-to-find-valid-certification-path-to-requested-target-error-even-after-c
	 * 
	 * For now, switch to non https since this is just test code
	 */
	public void send() {

		this.configureSSL();
		this.allowSelfSignedCert();

		URL url = null;
	    BufferedReader reader = null;
	    StringBuilder stringBuilder;
	    String desiredUrl = "https://23.21.226.246/csh/plan/index.html";
	    
	    try
	    {
	      // create the HttpURLConnection
	      url = new URL(desiredUrl);
	      HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
	      
	      // just want to do an HTTP GET here
	      connection.setRequestMethod("GET");
	      
	      // uncomment this if you want to write output to this url
	      //connection.setDoOutput(true);
	      
	      // give it 15 seconds to respond
	      connection.setReadTimeout(15*1000);
	      
	      connection.setDoOutput(true);
	      connection.setRequestProperty("API_REQUEST", "true");
	      connection.connect();

	        OutputStreamWriter out = new OutputStreamWriter(
	                                         connection.getOutputStream());
	        out.write("string=" + "test123");
	        out.close();
	        
	        BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                    connection.getInputStream()));
			String decodedString;
			while ((decodedString = in.readLine()) != null) {
			System.out.println(decodedString);
			}
			in.close();
	        
	    }
	    catch(Exception e) {
	    	System.out.println(e);
	    }
	}
}
