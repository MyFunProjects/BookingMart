package com.billing.billingmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.billing.billingmart.model.ProductBookingEntity;

@Repository
public interface BillingRepository extends JpaRepository<ProductBookingEntity, Long> {

}
