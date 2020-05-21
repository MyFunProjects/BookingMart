package com.billing.billingmart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billing.billingmart.model.BillingResponse;
import com.billing.billingmart.model.NewBillingRequest;
import com.billing.billingmart.model.NewBillingResponse;
import com.billing.billingmart.model.ProductBookingEntity;
import com.billing.billingmart.service.BillingService;

@RestController
@RequestMapping("/billingmart")

public class BillingController {

	@Autowired
	BillingService billingService;

	@GetMapping("/Getbills/{billing_id}")
	public ResponseEntity<List<BillingResponse>> getBillingDetails(@PathVariable("billing_id") Long pBillingid) {
		BillingResponse aProductDetails = billingService.getBillingDetails(pBillingid);
		List<BillingResponse> theBillingList = new ArrayList<BillingResponse>();
		if (aProductDetails != null) {
			theBillingList.add(aProductDetails);
			return new ResponseEntity<List<BillingResponse>>(theBillingList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<BillingResponse>>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/Newbill")
	public ResponseEntity<NewBillingResponse> createNewBilling(@RequestBody NewBillingRequest pNewBilling) {
		System.out.println("Entered controller" + pNewBilling);
		ProductBookingEntity newBilling = billingService.createNewBilling(pNewBilling);
		NewBillingResponse theBillingList = new NewBillingResponse();
		theBillingList.setTotalPrice(newBilling.getTotalPrice());
		theBillingList.setNewBillingID(Long.toString(newBilling.getBilling_id()));
		theBillingList.setComments(newBilling.getBillingComments());
		return new ResponseEntity<NewBillingResponse>(theBillingList, HttpStatus.OK);
	}
}