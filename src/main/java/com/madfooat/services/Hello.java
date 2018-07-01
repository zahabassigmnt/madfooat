package com.madfooat.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
	
	@RequestMapping("/")
	public String index() {
		
		   try {
			   Path path = Files.createTempFile("sample-file", ".txt");
		        
		        // writing sample data
		        Files.write(path, "Temporary content...".getBytes(StandardCharsets.UTF_8));
			   
			System.out.println("! New Directory Successfully Created !");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("! New Directory Error Created !");
		}
		   
        return "Greetings from Spring Boot!";
    }

}
