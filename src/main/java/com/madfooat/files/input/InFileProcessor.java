package com.madfooat.files.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class InFileProcessor {
	
	private HashMap<String,String> currCodes;
	
	public List<InRecordDTO> processInputFile(InputStream inputFS) throws Exception {
		List<InRecordDTO> inputList = new ArrayList<InRecordDTO>();
		currCodes = new HashMap<>();
		
	      BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
	      // skip the header of the csv
	      inputList =  br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
	      br.close();
	      
	      if(currCodes.keySet().size() > 1) {
	    	  //set file is not valid
	      }
	    return inputList ;
	}
	
	private Function<String, InRecordDTO> mapToItem = (line) -> {
		  String[] p = line.split(",");// a CSV has comma separated lines
		  InRecordDTO item = new InRecordDTO();
		  item.setValidationStatus(true);
		  try {
			  item.setTransactionId(p[0]);
			  
			  //validate TransactionId length
			  if(item.getTransactionId().length() != 36) {
				  throw new Exception("Invalid Transaction Id");
			  }
			  
			  //validate amount max value of 50K
			  item.setAmount(new BigDecimal(p[1]));
			  
			  if(item.getAmount().doubleValue() > 50000) {
				  throw new Exception("Invalid amount ");
			  }
			  
			  item.setCurrency(Currency.getInstance(p[2]));
			  //if all recordes have the same code the map size will be 1
			  currCodes.put(p[2], "");
			  
			  //validate number of decimals follows used currency in same record.
			  if(item.getAmount().scale() > item.getCurrency().getDefaultFractionDigits()) {
				  throw new Exception("number of decimals didn't follows used currency ");
			  }
			  
			  // validate Transaction Date: Future not allowed.
			  item.setTrxDate(new SimpleDateFormat("dd/MM/yyyy").parse(p[3]));
			  
			  if(new Date().before(item.getTrxDate())) {
				  throw new Exception("Transaction Date: Future not allowed");
			  }
			  
			  //validate not more than 10 digits
			  if(p[4].isEmpty() || p[4].length() > 10) {
				  throw new Exception("invalid customer id");
			  }
			  
			  item.setCustomerId(Integer.parseInt(p[4]));
			  
			  
			  
			  
		  }catch(Exception e) {
			  item.setErrorDescription(e.getMessage());
			  item.setValidationStatus(false);
		  }
		  return item;
		};
}
