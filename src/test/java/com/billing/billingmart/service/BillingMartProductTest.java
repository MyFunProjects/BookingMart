package com.billing.billingmart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import com.billing.billingmart.controller.BillingController;
import com.billing.billingmart.controller.CustomerController;
import com.billing.billingmart.controller.ProductController;
import com.billing.billingmart.model.BillingResponse;
import com.billing.billingmart.model.CustomerEntity;
import com.billing.billingmart.model.NewBillingRequest;
import com.billing.billingmart.model.ProductBookingEntity;
import com.billing.billingmart.model.ProductDetails;
import com.billing.billingmart.model.ProductEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest({ ProductController.class, CustomerController.class, BillingController.class })

@AutoConfigureMockMvc
public class BillingMartProductTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ProductService productService;

	@MockBean
	CustomerService customerService;

	@MockBean
	BillingService billingService;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getAllProductsTest() throws Exception {
		List<ProductEntity> theProductList = new ArrayList<ProductEntity>();
		ProductEntity aProduct = new ProductEntity(1L, "500", "wheat", "50");
		ProductEntity aProduct1 = new ProductEntity(2L, "700", "ragi", "80");
		theProductList.add(aProduct);
		theProductList.add(aProduct1);
		when(productService.getAllProducts()).thenReturn(theProductList);
		mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/billingmart/Products")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		assertEquals(2, theProductList.size());
	}

	@Test
	public void getProductsByIdTest() throws Exception {
		ProductEntity aProduct = new ProductEntity(1L, "wheat", "500", "50");
		when(productService.getAllProductsByID(any(Long.class))).thenReturn(aProduct);
		mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/billingmart/Products/{product_id}", 1)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.productName").value("wheat"));
	}

	@Test
	public void createOrUpdateProductTest() throws JsonProcessingException, Exception {
		ProductEntity aProduct = new ProductEntity(1L, "200", "maggi", "50");
		when(productService.CreateOrUpdateProduct(any(ProductEntity.class))).thenReturn(aProduct);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
				.post("http://localhost:8080/billingmart/CreateOrUpdateProduct").content(jsonRequestString(aProduct))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		String content = result.getResponse().getContentAsString();
		assertEquals("Product id :1", content);
	}

	@Test
	public void deleteProductTest() throws Exception {
		productService.deleteProductById(any(Long.class));
		mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/billingmart/DeleteProduct/{product_id}", 1)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		verify(productService, times(2)).deleteProductById(any(Long.class));
	}

	@Test
	public void getCustomerTest() throws Exception {
		List<CustomerEntity> theCustomerList = new ArrayList<CustomerEntity>();
		CustomerEntity aCustomer = new CustomerEntity(1L, "Abi", "9098099999");
		CustomerEntity aCustomer1 = new CustomerEntity(2L, "Akshaya", "9099869999");
		theCustomerList.add(aCustomer);
		theCustomerList.add(aCustomer1);
		when(customerService.getAllCustomers()).thenReturn(theCustomerList);
		mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/billingmart/Customers")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		assertEquals(2, theCustomerList.size());
	}

	@Test
	public void getCustomerByIdTest() throws Exception {
		CustomerEntity aCustomer = new CustomerEntity(1L, "Abi", "9098099999");
		when(customerService.getAllCustomersByID(any(Long.class))).thenReturn(aCustomer);
		mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/billingmart/Customers/{customer_id}", 1)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.customerName").value("Abi"));
	}

	@Test
	public void createOrUpdateCustomerTest() throws Exception {
		CustomerEntity aCustomer = new CustomerEntity(1L, "Abi", "9098099999");
		when(customerService.createOrUpdateCustomer(any(CustomerEntity.class))).thenReturn(aCustomer);
		System.out.println("Customer name" + aCustomer.getCustomer_id());
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
				.post("http://localhost:8080/billingmart/CreateOrUpdateCustomer").content(jsonRequestString(aCustomer))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		String content = result.getResponse().getContentAsString();
		assertEquals("Customer id :1", content);

	}

	@Test
	public void deleteCustomerTest() throws Exception {
		customerService.deleteCustomerById(any(Long.class));
		mockMvc.perform(
				MockMvcRequestBuilders.delete("http://localhost:8080/billingmart/DeleteCustomer/{customer_id}", 1)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		verify(customerService, times(2)).deleteCustomerById(any(Long.class));
	}

	@Test
	public void getBillingDetailsTest() throws Exception {
		List<ProductDetails> theProductDetailsList = new ArrayList<ProductDetails>();
		List<CustomerEntity> theCustomerEntityList = new ArrayList<CustomerEntity>();
		ProductDetails aProduct = new ProductDetails("1", "100");
		CustomerEntity aCustomer = new CustomerEntity(1L, "ABC", "88993039393");
		theProductDetailsList.add(aProduct);
		theCustomerEntityList.add(aCustomer);
		BillingResponse billing = new BillingResponse(1L, theProductDetailsList, theCustomerEntityList, "Purchased",
				"26-2-2020", "200");
		when(billingService.getBillingDetails(any(Long.class))).thenReturn(billing);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
				.get("http://localhost:8080/billingmart/Getbills/{billing_id}", 1).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String content = result.getResponse().getContentAsString();
		assertTrue(!content.isEmpty());
	}

	@Test
	public void createNewBillTest() throws Exception {
		ProductBookingEntity aNewBilling = new ProductBookingEntity(1L, 2L, "product", "Pending", "27-3-2020", "4000");
		ProductDetails aProduct = new ProductDetails("1", "200");
		List<ProductDetails> theProductList = new ArrayList<ProductDetails>();
		theProductList.add(aProduct);
		NewBillingRequest aBillingReq = new NewBillingRequest(1L, "Boopathy", "8393920202", theProductList);
		when(billingService.createNewBilling(any(NewBillingRequest.class))).thenReturn(aNewBilling);
		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/billingmart/Newbill")
				.content(jsonRequestString(aBillingReq)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.comments").value("Pending"));
	}

	@Test
	public void deleteBillTest() throws Exception {
		billingService.deleteBillById(any(Long.class));
		mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/billingmart/DeleteBill/{billing_id}", 1)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		verify(billingService, times(2)).deleteBillById(any(Long.class));
	}

	public static String jsonRequestString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			System.out.println(jsonContent);
			return jsonContent;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
