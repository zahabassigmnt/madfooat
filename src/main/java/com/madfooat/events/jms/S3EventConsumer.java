package com.madfooat.events.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class S3EventConsumer {
	
	  @JmsListener(destination = "${queue.b}")
	    public void processMessageB(@Payload String message) {
	       System.out.println("Processing {}  in queue b >> "  +  message);
	    }

}
