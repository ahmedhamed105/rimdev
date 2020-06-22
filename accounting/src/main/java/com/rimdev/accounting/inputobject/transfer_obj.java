package com.rimdev.accounting.inputobject;

import com.rimdev.accounting.Enttities.Account;

public class transfer_obj {
	String fromCustomercode;
	Account fromaccounts;
	String amount;
	String toCustomercode;
	Account toaccounts;
	String trxdesc;
	
	
	public transfer_obj() {
		super();
	}
	
	
	public String getTrxdesc() {
		return trxdesc;
	}


	public void setTrxdesc(String trxdesc) {
		this.trxdesc = trxdesc;
	}


	public String getFromCustomercode() {
		return fromCustomercode;
	}
	public void setFromCustomercode(String fromCustomercode) {
		this.fromCustomercode = fromCustomercode;
	}
	public Account getFromaccounts() {
		return fromaccounts;
	}
	public void setFromaccounts(Account fromaccounts) {
		this.fromaccounts = fromaccounts;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getToCustomercode() {
		return toCustomercode;
	}
	public void setToCustomercode(String toCustomercode) {
		this.toCustomercode = toCustomercode;
	}
	public Account getToaccounts() {
		return toaccounts;
	}
	public void setToaccounts(Account toaccounts) {
		this.toaccounts = toaccounts;
	}
	
	
	
}
