package com.madfooat.files.input;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

public class InRecordDTO {
	
private String transactionId;
private BigDecimal amount;
private  Currency  currency;
private Date trxDate;
private int customerId;
private boolean validationStatus;
private String errorDescription;


public String getErrorDescription() {
	return errorDescription;
}
public void setErrorDescription(String errorDescription) {
	this.errorDescription = errorDescription;
}
public String getTransactionId() {
	return transactionId;
}
public void setTransactionId(String transactionId) {
	this.transactionId = transactionId;
}
public BigDecimal getAmount() {
	return amount;
}
public void setAmount(BigDecimal amount) {
	this.amount = amount;
}

public Currency getCurrency() {
	return currency;
}
public void setCurrency(Currency currency) {
	this.currency = currency;
}
public Date getTrxDate() {
	return trxDate;
}
public void setTrxDate(Date trxDate) {
	this.trxDate = trxDate;
}
public int getCustomerId() {
	return customerId;
}
public void setCustomerId(int customerId) {
	this.customerId = customerId;
}
public boolean isValidationStatus() {
	return validationStatus;
}
public void setValidationStatus(boolean validationStatus) {
	this.validationStatus = validationStatus;
}





}
