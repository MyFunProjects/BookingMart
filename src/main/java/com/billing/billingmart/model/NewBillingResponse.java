package com.billing.billingmart.model;

public class NewBillingResponse {

	public String newBillingID;
	public String totalPrice;
	public String comments;

	public String getNewBillingID() {
		return newBillingID;
	}

	public void setNewBillingID(String newBillingID) {
		this.newBillingID = newBillingID;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "NewBillingResponse [newBillingID=" + newBillingID + ", totalPrice=" + totalPrice + ", comments="
				+ comments + "]";
	}
}
