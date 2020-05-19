package com.billing.billingmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.billing.billingmart.model.ProductEntity;

@Repository
public interface ProductRepository
        extends JpaRepository<ProductEntity, Long> {

}