package com.fusionent.assistant;

import java.io.IOException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.event.MessageCountAdapter;
import javax.mail.event.MessageCountEvent;

public class EmailCountListener extends MessageCountAdapter {

	
	public void messagesAdded(MessageCountEvent ev) {
	    Message[] msgs = ev.getMessages();
	    
	    this.processMessageCount(msgs.length);

	    // Just dump out the new messages
	    for (int i = 0; i < msgs.length; i++) {
			try {
			    this.processMessage(msgs[i]);
			} catch (IOException ioex) { 
			    ioex.printStackTrace();	
			} catch (MessagingException mex) {
			    mex.printStackTrace();
			}
	    }
	}
	
	/**
	 * 
	 * @param messageCount
	 */
	protected void processMessageCount(int messageCount) {

		System.out.println("Got " + messageCount + " new messages");
		
	}

	/**
	 * let the messages added handler stay clear of the processing
	 * 
	 * @param msg
	 * @throws IOException
	 * @throws MessagingException
	 */
	public void processMessage(Message msg) throws IOException, MessagingException {
		System.out.println("-----");
	    System.out.println("Message " +
		msg.getMessageNumber() + ":");
	    msg.writeTo(System.out);
	}
	

}
