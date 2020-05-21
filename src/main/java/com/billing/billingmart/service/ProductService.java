package com.billing.billingmart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billing.billingmart.model.ProductEntity;
import com.billing.billingmart.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public List<ProductEntity> getAllProducts() {
		List<ProductEntity> theProductList = productRepository.findAll();

		if (theProductList.size() > 0) {
			return theProductList;
		} else {
			return new ArrayList<ProductEntity>();
		}
	}

	public ProductEntity getAllProductsByID(Long pProductId) {
		Optional<ProductEntity> theProductIDList = productRepository.findById(pProductId);
		ProductEntity aProduct = new ProductEntity();
		if (theProductIDList.isPresent()) {
			aProduct.setProduct_id(theProductIDList.get().getProduct_id());
			aProduct.setProductName(theProductIDList.get().getProductName());
			aProduct.setPrice(theProductIDList.get().getPrice());
			aProduct.setQuantity(theProductIDList.get().getQuantity());
		} else {
			return null;
		}
		return aProduct;
	}

	public ProductEntity createNewProduct(ProductEntity pNewProduct) {
		ProductEntity theNewProduct = new ProductEntity();
		theNewProduct.setProductName(pNewProduct.getProductName());
		theNewProduct.setPrice(pNewProduct.getPrice());
		theNewProduct.setQuantity(pNewProduct.getQuantity());
		productRepository.save(theNewProduct);
		return theNewProduct;
	}

	public void deleteEmployeeById(Long pProductId) {
		Optional<ProductEntity> aProduct = productRepository.findById(pProductId);

		if (aProduct.isPresent()) {
			productRepository.deleteById(pProductId);
		}
	}
}