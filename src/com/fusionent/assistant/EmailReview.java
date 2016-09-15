package com.fusionent.assistant;

import java.util.*;


import javax.mail.*;
import javax.mail.event.*;
import javax.swing.JOptionPane;
import javax.activation.*;

import com.sun.mail.imap.*;

public class EmailReview {
	
	public void monitorImapMailbox() {
		
		String host = "owamail.am.sony.com";
//		String user = "zachary.kuhn@sony.com";
		String user = "zkuhn";
		String pass = "";
		String mbox = "Inbox";
		String freqMilli = "10000";
		
		
		//	"Usage: monitor <host> <user> <password> <mbox> <freq>");
		  
		

		try {
		    Properties props = getExchangeServerConfig();

		    pass = (String)JOptionPane.showInputDialog("Enter Password");

		    // Get a Session object
		    Session session = Session.getInstance(props, null);

		    session.setDebug(true);

		    // Get a Store object
		    Store store = session.getStore("imaps");

		    // Connect
		    store.connect(host,user,pass);

		    // Open a Folder
		    Folder folder = store.getFolder(mbox);
		    if (folder == null || !folder.exists()) {
		    	throw new Exception("Invalid folder");
		    }

		    folder.open(Folder.READ_WRITE);

		    // Add messageCountListener to listen for new messages
		    folder.addMessageCountListener(new EmailCountListener());
				
		    // Check mail once in "freq" MILLIseconds
		    int freq = Integer.parseInt(freqMilli);
		    boolean supportsIdle = false;
		    try {
				if (folder instanceof IMAPFolder) {
				    IMAPFolder f = (IMAPFolder)folder;
				    f.idle();
				    supportsIdle = true;
				}
		    } catch (FolderClosedException fex) {
		    	throw fex;
		    } catch (MessagingException mex) {
		    	supportsIdle = false;
		    }
		    //endless loop to prompt 
		    for (;;) {
				if (supportsIdle && folder instanceof IMAPFolder) {
				    IMAPFolder f = (IMAPFolder)folder;
				    f.idle();
				    System.out.println("IDLE done");
				} else {
				    Thread.sleep(freq); // sleep for freq milliseconds
				    System.out.println("SLEEP done");
				    
				    // This is to force the IMAP server to send us
				    // EXISTS notifications. 
				    folder.getMessageCount();
				}
		    }

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    
	}

	/**
	 * This is all the config in a properties that is necessary to connect to an 
	 * exchange server
	 * @return
	 */
	private Properties getExchangeServerConfig() {
		Properties props = System.getProperties();
		props.setProperty("mail.imap.starttls.enable", "true");
//		props.setProperty("ssl.SocketFactory.provider", "my.package.name.ExchangeSSLSocketFactory");
//		props.setProperty("mail.imap.socketFactory.class", "my.package.name.ExchangeSSLSocketFactory");
		return props;
	}

}
