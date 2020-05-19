package com.billing.billingmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.billing.billingmart.model.CustomerEntity;

@Repository
public interface CustomerRepository
        extends JpaRepository<CustomerEntity, Long> {

}
