package com.billing.billingmart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "TBL_CUSTOMER")
@DynamicUpdate(true)
public class CustomerEntity {

	public CustomerEntity() {
	}

	public CustomerEntity(Long customer_id, String customerName, String phNumber) {
		super();
		this.customer_id = customer_id;
		this.customerName = customerName;
		this.phNumber = phNumber;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customer_id;

	@Column(name = "first_name")
	private String customerName;

	@Column(name = "contact_number")
	private String phNumber;

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhNumber() {
		return phNumber;
	}

	public void setPhNumber(String phNumber) {
		this.phNumber = phNumber;
	}

	@Override
	public String toString() {
		return "CustomerEntity [customer_id=" + customer_id + ", customerName=" + customerName + ", phNumber="
				+ phNumber + "]";
	}
}
