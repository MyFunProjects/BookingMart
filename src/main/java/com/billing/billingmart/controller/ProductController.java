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

import com.billing.billingmart.model.ProductEntity;
import com.billing.billingmart.service.ProductService;

@RestController
@RequestMapping("/billingmart")

public class ProductController {
	@Autowired
	ProductService productService;

	@GetMapping("/Products")
	public ResponseEntity<List<ProductEntity>> getAllProducts() {
		List<ProductEntity> theProductList = productService.getAllProducts();

		return new ResponseEntity<List<ProductEntity>>(theProductList, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/Products/{product_id}")
	public ResponseEntity<ProductEntity> getAllCustomersByID(@PathVariable("product_id") Long pProductId) {
		ProductEntity theProductIDList = productService.getAllProductsByID(pProductId);
		if (theProductIDList != null) {
			return new ResponseEntity<ProductEntity>(theProductIDList, new HttpHeaders(), HttpStatus.OK);
		} else {
			return new ResponseEntity<ProductEntity>(theProductIDList,HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/CreateOrUpdateProduct")
	public ResponseEntity<String> createOrUpdateProduct(@RequestBody ProductEntity pNewProduct) {
		ProductEntity aNewCustomer = productService.CreateOrUpdateProduct(pNewProduct);
		return new ResponseEntity<String>("Product id :" +Long.toString(aNewCustomer.getProduct_id()), HttpStatus.OK);
	}
	
	@DeleteMapping("/DeleteProduct/{product_id}")
    public String deleteProductById(@PathVariable("product_id") Long pProductId)
                                                   {
		productService.deleteProductById(pProductId);
        return "Deleted Product Successfully";
    }
 
}
