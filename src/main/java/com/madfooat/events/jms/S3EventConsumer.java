package com.madfooat.events.jms;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.event.S3EventNotification;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.madfooat.files.input.InFileProcessor;
import com.madfooat.files.input.InRecordDTO;
import com.madfooat.files.output.OutFileProcessor;
import com.madfooat.jpa.model.MerchantFile;
import com.madfooat.jpa.repo.MerchantFilesRepo;

@Component
public class S3EventConsumer {

	@Autowired
	private AmazonS3 amazonS3Client;
	
	@Autowired
	private InFileProcessor inFileProcessor; 
	
	@Autowired
	private OutFileProcessor outFileProcessor;
	
	@Autowired
	private MerchantFilesRepo merRepo;

	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@JmsListener(destination = "${queue.b}")
	public void processMessageB(@Payload String message) {
		System.out.println("Processing {}  in queue b >> " + message);
		try {
			S3EventNotification e = S3EventNotification.parseJson(message);
			
			
			String incomingFilename =  new File(e.getRecords().get(0).getS3().getObject().getKey()).getName(); 
			if(incomingFilename.startsWith("In_") && !incomingFilename.endsWith("_Out.csv")) {
			
			//Insert File with status uploaded
				MerchantFile file = new MerchantFile();
				file.setInput_s3_key(e.getRecords().get(0).getS3().getObject().getKey());
				file.setMerchant_code("X");
				file.setStatus("Uploaded and under processing");
				merRepo.save(file);	
			
			S3Object s3Object = amazonS3Client.getObject(bucket, e.getRecords().get(0).getS3().getObject().getKey());
			S3ObjectInputStream  is = s3Object.getObjectContent();
			List<InRecordDTO>  items = inFileProcessor.processInputFile(is);
			File outFile = outFileProcessor.processOutFile(items, incomingFilename);
			amazonS3Client.putObject(bucket, e.getRecords().get(0).getS3().getObject().getKey() + "_Out.csv", outFile);
			System.out.println("Uploaded to S3");
			}
			else {
				System.out.println("No Uploading gomes");
			}
			
		} catch (Exception e1) {

			e1.printStackTrace();
		}

	}

}
