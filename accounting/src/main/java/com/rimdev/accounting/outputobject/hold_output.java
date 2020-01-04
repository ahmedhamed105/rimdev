package com.rimdev.accounting.outputobject;

import java.math.BigDecimal;

public class hold_output {

	int Error_code;
	String Error_desc;
	String Acct_no;
	String Customer_no;
	String Currency;
	BigDecimal amount;
	BigDecimal Current_balance;
	BigDecimal Avl_balance;
	String Reference_no;
	int hold_id;

	public hold_output() {
		super();
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getCurrent_balance() {
		return Current_balance;
	}

	public void setCurrent_balance(BigDecimal current_balance) {
		Current_balance = current_balance;
	}

	public BigDecimal getAvl_balance() {
		return Avl_balance;
	}

	public void setAvl_balance(BigDecimal avl_balance) {
		Avl_balance = avl_balance;
	}

	public String getReference_no() {
		return Reference_no;
	}

	public void setReference_no(String reference_no) {
		Reference_no = reference_no;
	}

	public int getHold_id() {
		return hold_id;
	}

	public void setHold_id(int hold_id) {
		this.hold_id = hold_id;
	}

}
