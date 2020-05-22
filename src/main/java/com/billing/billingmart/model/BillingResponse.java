package com.billing.billingmart.model;

import java.util.Date;
import java.util.List;

public class BillingResponse {

	public Long billing_id;
	public List<ProductDetails> productDetails;
	public List<CustomerEntity> customerDetails;
	private String billingComments;
	private String billingDate;
	private String totalPrice;

	public Long getBilling_id() {
		return billing_id;
	}

	public void setBilling_id(Long billing_id) {
		this.billing_id = billing_id;
	}

	public List<ProductDetails> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(List<ProductDetails> productDetails) {
		this.productDetails = productDetails;
	}

	public List<CustomerEntity> getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(List<CustomerEntity> customerDetails) {
		this.customerDetails = customerDetails;
	}

	public String getBillingComments() {
		return billingComments;
	}

	public void setBillingComments(String billingComments) {
		this.billingComments = billingComments;
	}

	public String getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(String billingDate) {
		this.billingDate = billingDate;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "BillingResponse [billing_id=" + billing_id + ", productDetails=" + productDetails + ", customerDetails="
				+ customerDetails + ", billingComments=" + billingComments + ", billingDate=" + billingDate
				+ ", totalPrice=" + totalPrice + "]";
	}
}
