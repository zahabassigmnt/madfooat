package com.madfooat.jpa.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Merchant_Files")
public class MerchantFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@Column(columnDefinition = "input_s3_key")
	private String input_s3_key;
	
	@Column(columnDefinition = "output_s3_key")
	private String output_s3_key;

	@Column(columnDefinition = "status")
	private String status;	
	
	
	@Column(columnDefinition = "merchant_code")
	private String merchant_code;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getInput_s3_key() {
		return input_s3_key;
	}


	public void setInput_s3_key(String input_s3_key) {
		this.input_s3_key = input_s3_key;
	}


	public String getOutput_s3_key() {
		return output_s3_key;
	}


	public void setOutput_s3_key(String output_s3_key) {
		this.output_s3_key = output_s3_key;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getMerchant_code() {
		return merchant_code;
	}


	public void setMerchant_code(String merchant_code) {
		this.merchant_code = merchant_code;
	}	
	
	

}
