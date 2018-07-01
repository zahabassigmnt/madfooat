package com.madfooat.files.output;

import java.math.BigDecimal;

public class OutRecordDTO {
	
	private  String status;
	private int trxNo;
	private BigDecimal totalAmount;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTrxNo() {
		return trxNo;
	}
	public void setTrxNo(int trxNo) {
		this.trxNo = trxNo;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	

}
