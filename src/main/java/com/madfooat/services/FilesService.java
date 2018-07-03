package com.madfooat.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.madfooat.jpa.repo.MerchantFilesRepo;


@RestController
@RequestMapping("/files")
public class FilesService {
	
	@Autowired
	private AmazonS3 amazonS3Client;
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	@Autowired
	private MerchantFilesRepo merRepo;
	
	
	@PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) throws IOException {
		
	
		
		Path path = Files.createTempFile(file.getOriginalFilename(), ".csv");
        Files.write(path, file.getBytes());
        amazonS3Client.putObject(bucket, "merchants/X/In_" +file.getOriginalFilename() , path.toFile());
        System.out.println("Input File uploaded to S3");
		return "";
	}
	
	@GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws IOException {
		
	
		
		
		String key = merRepo.findById(Integer.parseInt(fileId)).get().getOutput_s3_key();
		S3Object s3Object = amazonS3Client.getObject(bucket, key);
		
		byte[] filebyte = IOUtils.toByteArray(s3Object.getObjectContent());
		Path path = Files.createTempFile("Temp", ".csv");
        Files.write(path, filebyte);
		String fileName = new File(key).getName();
		
		InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
		HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        
		headers.add("content-disposition", "attachment; filename=\"" + fileName  +"\"");

	    return ResponseEntity.ok()
	            .headers(headers)
	            .contentLength(filebyte.length)
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .body(resource);
	}
	
	

}
