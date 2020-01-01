package com.rimdev.accounting.outputobject;

public class Transaction_output {
	
	Boolean Trx_status;
	int     Error_code;
	String  Error_desc;
	String  Acct_no;
	String  Customer_no;
	String  Currency;
	double  Current_balance;
	double  Avl_balance;
	String  Reference_no;
	
	
	public Transaction_output() {
		super();
	}
	
	


	public String getReference_no() {
		return Reference_no;
	}




	public void setReference_no(String reference_no) {
		Reference_no = reference_no;
	}




	public Boolean getTrx_status() {
		return Trx_status;
	}


	public void setTrx_status(Boolean trx_status) {
		Trx_status = trx_status;
	}


	public int getError_code() {
		return Error_code;
	}


	public void setError_code(int error_code) {
		Error_code = error_code;
	}


	public String getError_desc() {
		return Error_desc;
	}


	public void setError_desc(String error_desc) {
		Error_desc = error_desc;
	}


	public String getAcct_no() {
		return Acct_no;
	}


	public void setAcct_no(String acct_no) {
		Acct_no = acct_no;
	}


	public String getCustomer_no() {
		return Customer_no;
	}


	public void setCustomer_no(String customer_no) {
		Customer_no = customer_no;
	}


	public String getCurrency() {
		return Currency;
	}


	public void setCurrency(String currency) {
		Currency = currency;
	}


	public double getCurrent_balance() {
		return Current_balance;
	}


	public void setCurrent_balance(double current_balance) {
		Current_balance = current_balance;
	}


	public double getAvl_balance() {
		return Avl_balance;
	}


	public void setAvl_balance(double avl_balance) {
		Avl_balance = avl_balance;
	}
	


}
