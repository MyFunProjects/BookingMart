package com.billing.billingmart.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_PRODUCT_BOOKING")

public class ProductBookingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long billing_id;

	@Column(name = "customer_id")
	private Long customerID;

	@Column(name = "product_details")
	private String productDetails;

	@Column(name = "billing_comments")
	private String billingComments;

	@Column(name = "billing_date")
	private Date billingDate;

	@Column(name = "total_price")
	private String totalPrice;

	public Long getBilling_id() {
		return billing_id;
	}

	public void setBilling_id(Long billing_id) {
		this.billing_id = billing_id;
	}

	public Long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

	public String getProducDetails() {
		return productDetails;
	}

	public void setProducDetails(String productDetails) {
		this.productDetails = productDetails;
	}

	public String getBillingComments() {
		return billingComments;
	}

	public void setBillingComments(String billingComments) {
		this.billingComments = billingComments;
	}

	public Date getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(Date billingDate) {
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
		return "ProductBookingEntity [billing_id=" + billing_id + ", customerID=" + customerID + ", productDetails="
				+ productDetails + ", billingComments=" + billingComments + ", billingDate=" + billingDate
				+ ", totalPrice=" + totalPrice + "]";
	}
}
