package com.billing.billingmart.model;

import java.util.List;

public class NewBillingRequest {

	public NewBillingRequest() {
	}

	public Long customerID;
	public String customerName;
	public String phoneNumber;
	public List<ProductDetails> productDetails;

	public NewBillingRequest(Long customerID, String customerName, String phoneNumber,
			List<ProductDetails> productDetails) {
		super();
		this.customerID = customerID;
		this.customerName = customerName;
		this.phoneNumber = phoneNumber;
		this.productDetails = productDetails;
	}

	public Long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<ProductDetails> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(List<ProductDetails> productDetails) {
		this.productDetails = productDetails;
	}

	@Override
	public String toString() {
		return "NewBillingRequest [customerID=" + customerID + ", customerName=" + customerName + ", phoneNumber="
				+ phoneNumber + ", productDetails=" + productDetails + "]";
	}

}
