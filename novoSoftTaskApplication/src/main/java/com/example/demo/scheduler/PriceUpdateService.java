package com.example.demo.scheduler;

import com.example.demo.entity.ProductEntity;
import com.example.demo.external.ExternalApiService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PriceUpdateService {

	
	
    private final ExternalApiService externalApiService;
    private final ProductService productService;


    public PriceUpdateService(ExternalApiService externalApiService, ProductService productService) {
        this.externalApiService = externalApiService;
        this.productService = productService;
    }

    @Scheduled(fixedRate = 86400000) // Runs every 24 hours
    public void updateProductPrices() {
        int defaultPage = 0;
        int defaultSize = 100;
        
        Page<ProductEntity> productPage = productService.getAllProducts(defaultPage, defaultSize);
        
        while (productPage.hasContent()) {
            for (ProductEntity product : productPage.getContent()) {
                // Fetch the latest price and update the product
                Double newPrice = externalApiService.fetchLatestPrice(product.getId().toString());
                product.setPrice(newPrice);
                productService.updateProduct(product.getId(), product);
            }
            
            // Fetch the next page
            defaultPage++;
            productPage = productService.getAllProducts(defaultPage, defaultSize);
        }
    }
}
