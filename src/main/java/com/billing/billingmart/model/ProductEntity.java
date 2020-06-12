package com.billing.billingmart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "TBL_PRODUCT")
@DynamicUpdate(true)
public class ProductEntity {

	public ProductEntity() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long product_id;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_price")
	private String price;

	@Column(name = "avail_quantity")
	private String quantity;

	public ProductEntity(Long product_id, String productName, String price, String quantity) {
		
		this.product_id = product_id;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProductEntity [product_id=" + product_id + ", productName=" + productName + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}
}
