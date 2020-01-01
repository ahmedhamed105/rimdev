package com.rimdev.accounting.inputobject;

public class transaction_input {
	
	String Customer_no;
	String Currency;
	String acct_no;
    double amount;
    String Trx_type;
    String Trx_flow;
    String  Reference_no;
    
    
	public transaction_input() {
		super();
	}
	
	
	


	public String getReference_no() {
		return Reference_no;
	}


	public void setReference_no(String reference_no) {
		Reference_no = reference_no;
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


	public String getAcct_no() {
		return acct_no;
	}


	public void setAcct_no(String acct_no) {
		this.acct_no = acct_no;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getTrx_type() {
		return Trx_type;
	}


	public void setTrx_type(String trx_type) {
		Trx_type = trx_type;
	}


	public String getTrx_flow() {
		return Trx_flow;
	}


	public void setTrx_flow(String trx_flow) {
		Trx_flow = trx_flow;
	}
    
	
	
    
    

}
