package com.billing.billingmart.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.billing.billingmart.model.ProductDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtility  {

	private final static ObjectMapper objectMapper = new ObjectMapper();

	public static String convertToDatabaseColumn(List<ProductDetails> productDetailsList) {
        String jsonString = "";
        try {
            System.out.println("Start convertToDatabaseColumn");

            // convert list of POJO to json
            jsonString = objectMapper.writeValueAsString(productDetailsList);
            System.out.println("convertToDatabaseColumn" + jsonString);

        } catch (JsonProcessingException ex) {
            System.out.println(ex.getMessage());
        }
        return jsonString;
	}


	public static List<ProductDetails> convertToEntityAttribute(String producDetails) {
		List<ProductDetails> payload = new ArrayList<ProductDetails>();
		try {
			System.out.println("Start convertToEntityAttribute");

			// convert json to list of POJO
			payload = objectMapper.readValue(producDetails ,new TypeReference<List<ProductDetails>>(){});
			System.out.println("JsonDocumentsConverter.convertToDatabaseColumn" + payload);

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		return payload;

	}

	


}
