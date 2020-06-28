package com.rimdev.accounting.outputobject;

import java.math.BigDecimal;

public class Transaction_output {
	
	int     error_code;
	String  error_desc;
	String  acct_no;
	String  customer_no;
	String  currency;
	BigDecimal  amount;
	BigDecimal  current_balance;
	BigDecimal  avl_balance;
	String  reference_no;
	
	
	public Transaction_output() {
		super();
	}
	
	


	public Transaction_output(BigDecimal amount, String reference_no) {
		this.amount = amount;
		this.reference_no = reference_no;
	}




	public int getError_code() {
		return error_code;
	}




	public void setError_code(int error_code) {
		this.error_code = error_code;
	}




	public String getError_desc() {
		return error_desc;
	}




	public void setError_desc(String error_desc) {
		this.error_desc = error_desc;
	}




	public String getAcct_no() {
		return acct_no;
	}




	public void setAcct_no(String acct_no) {
		this.acct_no = acct_no;
	}




	public String getCustomer_no() {
		return customer_no;
	}




	public void setCustomer_no(String customer_no) {
		this.customer_no = customer_no;
	}




	public String getCurrency() {
		return currency;
	}




	public void setCurrency(String currency) {
		this.currency = currency;
	}




	public BigDecimal getAmount() {
		return amount;
	}




	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}




	public BigDecimal getCurrent_balance() {
		return current_balance;
	}




	public void setCurrent_balance(BigDecimal current_balance) {
		this.current_balance = current_balance;
	}




	public BigDecimal getAvl_balance() {
		return avl_balance;
	}




	public void setAvl_balance(BigDecimal avl_balance) {
		this.avl_balance = avl_balance;
	}




	public String getReference_no() {
		return reference_no;
	}




	public void setReference_no(String reference_no) {
		this.reference_no = reference_no;
	}


}
