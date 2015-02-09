package com.niksoft.dao;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="paymenttransactions")
@XmlSeeAlso({ PaymentTransaction.class })
public class PaymentTransactions extends ArrayList<PaymentTransaction>  {
	
	private static final long serialVersionUID = -3768098627977847577L;

	@XmlElement(name = "paymenttransaction")
	public List<PaymentTransaction> getPaymentTransactions() {
		return this;
	}
}
