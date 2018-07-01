package com.madfooat.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madfooat.jpa.model.Merchant;
import com.madfooat.jpa.repo.MerchantRepo;

@RestController
public class Hello {
	
	@Autowired
    private MerchantRepo merchantRepo;
	
	@RequestMapping("/")
	public String index() {
		
		   try {
			   
			   List<Merchant> list = merchantRepo.findAll();
			   System.out.println("List >> " + list.size());
			   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("! New Directory Error Created !");
		}
		   
        return "Greetings from Spring Boot!";
    }

}
