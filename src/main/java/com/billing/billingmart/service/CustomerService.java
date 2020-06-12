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
		Optional<CustomerEntity> aCustomerID = customerRepository.findById(pCustomerId);
		CustomerEntity aCustomer = new CustomerEntity();
		if (aCustomerID.isPresent()) {
			aCustomer.setCustomer_id(aCustomerID.get().getCustomer_id());
			aCustomer.setCustomerName(aCustomerID.get().getCustomerName());
			aCustomer.setPhNumber(aCustomerID.get().getPhNumber());
		} else {
			return null;
		}
		return aCustomer;
	}

	public CustomerEntity createOrUpdateCustomer(CustomerEntity pNewCustomer) {
		if (pNewCustomer.getCustomer_id() == null) {
			pNewCustomer = customerRepository.save(pNewCustomer);
			return pNewCustomer;
		} else {

			Optional<CustomerEntity> aCustomerID = customerRepository.findById(pNewCustomer.getCustomer_id());
			if (aCustomerID.isPresent()) {
				CustomerEntity theNewCustomer = aCustomerID.get();
				theNewCustomer.setCustomerName(pNewCustomer.getCustomerName());
				theNewCustomer.setPhNumber(pNewCustomer.getPhNumber());
				theNewCustomer = customerRepository.save(theNewCustomer);
				return theNewCustomer;
			}

			else {
				pNewCustomer = customerRepository.save(pNewCustomer);
				return pNewCustomer;
			}
		}
	}	
	
	

	public void deleteCustomerById(Long pCustomerid) {
		Optional<CustomerEntity> aProduct = customerRepository.findById(pCustomerid);
		if (aProduct.isPresent()) {
			customerRepository.deleteById(pCustomerid);
		}
	}
	
}