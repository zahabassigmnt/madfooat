package com.madfooat.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Hello {
	
//	@Autowired
//    private MerchantRepo merchantRepo;
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String index() {
		
//		   try {
//			   
//			   List<Merchant> list = merchantRepo.findAll();
//			   System.out.println("List >> " + list.size());
//			   
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("! New Directory Error Created !");
//		}
		   
        return "formpage";
    }

}
