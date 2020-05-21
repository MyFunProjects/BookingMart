package com.billing.billingmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.billing.billingmart.model.ProductEntity;

@Repository
public interface ProductRepository
        extends JpaRepository<ProductEntity, Long> {
	
	@Modifying
	@Query("update ProductEntity p set p.quantity = ?1 where p.product_id = ?2")
	int setQuantity(String quantity, Long id);
	
	

}