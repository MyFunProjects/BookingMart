package com.billing.billingmart.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billing.billingmart.model.BillingResponse;
import com.billing.billingmart.model.CustomerEntity;
import com.billing.billingmart.model.NewBillingRequest;
import com.billing.billingmart.model.ProductBookingEntity;
import com.billing.billingmart.model.ProductDetails;
import com.billing.billingmart.model.ProductEntity;
import com.billing.billingmart.repository.BillingRepository;
import com.billing.billingmart.repository.CustomerRepository;
import com.billing.billingmart.repository.ProductRepository;

@Service
public class BillingService {

	@Autowired
	BillingRepository billingRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ProductRepository productRepository;

	public BillingResponse getBillingDetails(Long pBillingid) {
		BillingResponse aBillResponse = new BillingResponse();

		CustomerEntity aCustomerDetails = new CustomerEntity();
		List<CustomerEntity> theCustomerDetails = new ArrayList<CustomerEntity>();
		List<ProductDetails> theProductList = new ArrayList<ProductDetails>();

		Optional<ProductBookingEntity> aNewBilling = billingRepository.findById(pBillingid);

		if (aNewBilling.isPresent()) {
			Optional<CustomerEntity> aCustomerId = customerRepository.findById(aNewBilling.get().getCustomerID());
			if (aCustomerId.isPresent()) {
				aCustomerDetails.setCustomer_id(aCustomerId.get().getCustomer_id());
				aCustomerDetails.setCustomerName(aCustomerId.get().getCustomerName());
				aCustomerDetails.setPhNumber(aCustomerId.get().getPhNumber());
				theCustomerDetails.add(aCustomerDetails);
				aBillResponse.setCustomerDetails(theCustomerDetails);

				aBillResponse.setBilling_id(pBillingid);
				aBillResponse.setBillingComments(aNewBilling.get().getBillingComments());
				aBillResponse.setBillingDate(aNewBilling.get().getBillingDate());
				aBillResponse.setTotalPrice(aNewBilling.get().getTotalPrice());

				String sConvertor = aNewBilling.get().getProducDetails();

				if (!sConvertor.isEmpty()) {
					theProductList = JsonUtility.convertToEntityAttribute(sConvertor);
					aBillResponse.setProductDetails(theProductList);
				}
			}
		}
		return aBillResponse;
	}

	@Transactional
	public ProductBookingEntity createNewBilling(NewBillingRequest pNewBilling) {
		Date date = new Date();
		CustomerEntity aCustomerEntity = new CustomerEntity();
		List<ProductDetails> theProductDetailsList = new ArrayList<ProductDetails>();
		ProductBookingEntity aProductBookingEntity = new ProductBookingEntity();
		if (pNewBilling.getCustomerID() == null) {
			generateCustomerID(pNewBilling, aCustomerEntity, aProductBookingEntity);
		} else {
			Optional<ProductBookingEntity> aNewBilling = billingRepository.findById(pNewBilling.getCustomerID());
			if (aNewBilling.isPresent()) {
				aProductBookingEntity.setCustomerID(pNewBilling.getCustomerID());
			} else {
				generateCustomerID(pNewBilling, aCustomerEntity, aProductBookingEntity);
			}
		}

		theProductDetailsList = pNewBilling.getProductDetails();

		String jsonString = JsonUtility.convertToDatabaseColumn(theProductDetailsList);
		System.out.println("the string value is " + jsonString);
		aProductBookingEntity.setProducDetails(jsonString);
		aProductBookingEntity.setBillingDate(date);		
		aProductBookingEntity.setTotalPrice(calculatePrice(theProductDetailsList, aProductBookingEntity));
		billingRepository.save(aProductBookingEntity);
		return aProductBookingEntity;

	}

	private void generateCustomerID(NewBillingRequest pNewBilling, CustomerEntity aCustomerEntity,
			ProductBookingEntity productBookingEntity) {
		aCustomerEntity.setCustomerName(pNewBilling.getCustomerName());
		aCustomerEntity.setPhNumber(pNewBilling.getPhoneNumber());
		customerRepository.save(aCustomerEntity);
		productBookingEntity.setCustomerID(aCustomerEntity.getCustomer_id());
	}

	private String calculatePrice(List<ProductDetails> theProductDetailsList,
			ProductBookingEntity pProductBookingEntity) {
		Integer totalPrice = 0;
		StringBuilder stringbuilder = new StringBuilder();
		boolean isDescrepencyExist =false;
		//stringbuilder.append("Happy shopping...!");
		pProductBookingEntity.setBillingComments(stringbuilder.toString());
		for (ProductDetails aProductDetails : theProductDetailsList) {
			Integer newQuantity = 0;
			Optional<ProductEntity> aproduct = productRepository.findById(Long.valueOf(aProductDetails.getProductId()));
			if (Integer.valueOf(aproduct.get().getQuantity()) > 0
					&& Integer.valueOf(aProductDetails.getQuantity()) < Integer.valueOf(aproduct.get().getQuantity())) {
				totalPrice = totalPrice
						+ (Integer.valueOf(aProductDetails.getQuantity()) * Integer.valueOf(aproduct.get().getPrice()));
				newQuantity = Integer.valueOf(aproduct.get().getQuantity())
						- Integer.valueOf(aProductDetails.getQuantity());
				Optional<ProductEntity> aProductEntity = productRepository
						.findById(Long.valueOf(aProductDetails.getProductId()));
				if (aProductEntity.isPresent()) {
					productRepository.setQuantity(newQuantity.toString(), aProductEntity.get().getProduct_id());
					
					
				}
			} else {
				isDescrepencyExist=true;
				stringbuilder.append("Product Descrepency Exist for productId: " + aProductDetails.getProductId())
						.append(",");
			}
		}
	if(isDescrepencyExist) {
		String val = stringbuilder.toString().trim();		
		pProductBookingEntity.setBillingComments(val.substring(0,val.length()-1));
		
		}
	else {
		pProductBookingEntity.setBillingComments("Happy Shopping...!");
	}
		return totalPrice.toString();
	}

	public void deleteBillById(Long pBillingid) {
		Optional<ProductBookingEntity> aBilling = billingRepository.findById(pBillingid);
		if (aBilling.isPresent()) {
			billingRepository.deleteById(pBillingid);
		}

	}
}
