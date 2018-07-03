package com.madfooat.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;


@RestController
@RequestMapping("/uploader")
public class UploadService {
	
	@Autowired
	private AmazonS3 amazonS3Client;
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	
	
	@PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) throws IOException {
		
	
		
		Path path = Files.createTempFile(file.getOriginalFilename(), ".csv");
        Files.write(path, file.getBytes());
        amazonS3Client.putObject(bucket, "merchants/X/In_" +file.getOriginalFilename() , path.toFile());
        System.out.println("Input File uploaded to S3");
		
		
		return "";
	}
	
	

}
