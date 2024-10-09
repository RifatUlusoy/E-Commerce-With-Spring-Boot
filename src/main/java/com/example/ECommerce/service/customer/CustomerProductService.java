package com.example.ECommerce.service.customer;

import java.util.List;

import com.example.ECommerce.dto.ProductDto;

public interface CustomerProductService {
	
	List<ProductDto> searchProductByTitle(String title);
	List<ProductDto> getAllProducts();
	
}
