package com.example.demo.external;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;

@Service
public class ExternalApiService {

    private final RestTemplate restTemplate;
    
    @Value("${external.api.url}")
    private String apiUrl;

    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "productPrices", key = "#productId")
    public Double fetchLatestPrice(String productId) {
        String url = apiUrl + "/product/" + productId + "/price";
        try {
            // Fetch and map the response to PriceResponse
            PriceResponse response = restTemplate.getForObject(url, PriceResponse.class);
            return response != null ? response.getPrice() : null;
        } catch (HttpClientErrorException e) {
            System.err.println("Error fetching price for productId " + productId + ": " + e.getStatusCode() + " " + e.getResponseBodyAsString());
            return null; // Handle this case as needed
        }
    }
}

