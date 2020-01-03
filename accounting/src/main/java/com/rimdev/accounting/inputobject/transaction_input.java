package com.rimdev.accounting.inputobject;

import java.math.BigDecimal;

public class transaction_input {
	String Customer_id;
	String reference_no;
	String Currency;
	String acct_no;
	BigDecimal amount;
	int Trantypecode;
	String Trx_flow;
	String Trx_desc;
	int hold_id;

	public transaction_input() {
		super();
	}

	public String getCustomer_id() {
		return Customer_id;
	}

	public void setCustomer_id(String customer_id) {
		Customer_id = customer_id;
	}

	public String getReference_no() {
		return reference_no;
	}

	public void setReference_no(String reference_no) {
		this.reference_no = reference_no;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getTrantypecode() {
		return Trantypecode;
	}

	public void setTrantypecode(int trantypecode) {
		Trantypecode = trantypecode;
	}

	public String getTrx_flow() {
		return Trx_flow;
	}

	public void setTrx_flow(String trx_flow) {
		Trx_flow = trx_flow;
	}

	public String getTrx_desc() {
		return Trx_desc;
	}

	public void setTrx_desc(String trx_desc) {
		Trx_desc = trx_desc;
	}

	public int getHold_id() {
		return hold_id;
	}

	public void setHold_id(int hold_id) {
		this.hold_id = hold_id;
	}

}
