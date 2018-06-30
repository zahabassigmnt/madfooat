package com.madfooat.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
	
	@RequestMapping("/")
	public String index() {
		 Path dirPathObj = Paths.get("/zahab");
		 String workingDir = System.getProperty("user.dir");
		   System.out.println("Current working directory : " + workingDir);
		   try {
			   dirPathObj = Files.createDirectories(dirPathObj);
			   System.out.println("dirPathObj >> "+ dirPathObj);
			   
			System.out.println("! New Directory Successfully Created !");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("! New Directory Error Created !");
		}
		   
        return "Greetings from Spring Boot! hi";
    }

}
