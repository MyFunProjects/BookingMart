package com.billing.billingmart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billing.billingmart.model.CustomerEntity;
import com.billing.billingmart.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	public List<CustomerEntity> getAllCustomers() {
		List<CustomerEntity> theCustomerList = customerRepository.findAll();

		if (theCustomerList.size() > 0) {
			return theCustomerList;
		} else {
			return new ArrayList<CustomerEntity>();
		}
	}

	public CustomerEntity getAllCustomersByID(Long pCustomerId) {
		Optional<CustomerEntity> theCustomerIDList = customerRepository.findById(pCustomerId);
		CustomerEntity aCustomer = new CustomerEntity();
		if (theCustomerIDList.isPresent()) {
			aCustomer.setCustomer_id(theCustomerIDList.get().getCustomer_id());
			aCustomer.setCustomerName(theCustomerIDList.get().getCustomerName());
			aCustomer.setPhNumber(theCustomerIDList.get().getPhNumber());
		} else {
			return null;
		}
		return aCustomer;
	}

	public CustomerEntity createNewCustomer(CustomerEntity pNewCustomer) {
		CustomerEntity theNewCustomer = new CustomerEntity();
		theNewCustomer.setCustomerName(pNewCustomer.getCustomerName());
		theNewCustomer.setPhNumber(pNewCustomer.getPhNumber());
		customerRepository.save(theNewCustomer);
		return theNewCustomer;
	}
}