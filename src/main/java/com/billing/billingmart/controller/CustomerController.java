package com.billing.billingmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billing.billingmart.model.CustomerEntity;
import com.billing.billingmart.service.CustomerService;

@RestController
@RequestMapping("/billingmart")

public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping("/Customers")
	public ResponseEntity<List<CustomerEntity>> getAllCustomers() {
		List<CustomerEntity> theCustomerList = customerService.getAllCustomers();

		return new ResponseEntity<List<CustomerEntity>>(theCustomerList, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/Customers/{customer_id}")
	public ResponseEntity<CustomerEntity> getAllCustomersByID(@PathVariable("customer_id") Long pCustomerId) {
		CustomerEntity theCustomerIDList = customerService.getAllCustomersByID(pCustomerId);
		if (theCustomerIDList != null) {
			return new ResponseEntity<CustomerEntity>(theCustomerIDList, new HttpHeaders(), HttpStatus.OK);
		} else {
			return new ResponseEntity<CustomerEntity>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/CreateOrUpdateCustomer")
	public ResponseEntity<String> createOrUpdateCustomer(@RequestBody CustomerEntity pNewCustomer) {
		CustomerEntity aNewCustomer = customerService.createOrUpdateCustomer(pNewCustomer);
		return new ResponseEntity<String>("Customer id :" +Long.toString(aNewCustomer.getCustomer_id()), HttpStatus.OK);
	}
	
	@DeleteMapping("/DeleteCustomer/{customer_id}")
    public String deleteCustomerById(@PathVariable("customer_id") Long pCustomerid)
                                                   {
		customerService.deleteCustomerById(pCustomerid);
        return "Deleted Customer successfully";
    }
}
