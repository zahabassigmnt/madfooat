package com.madfooat.events.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.event.S3EventNotification;

@Component
public class S3EventConsumer {
	
	  @JmsListener(destination = "${queue.b}")
	    public void processMessageB(@Payload String message) {
	       System.out.println("Processing {}  in queue b >> "  +  message);
	       S3EventNotification e = S3EventNotification.parseJson(message);
	       System.out.println(e.getRecords().get(0).getS3().getObject().getKey());
	    
	       
	       
	    }

}
