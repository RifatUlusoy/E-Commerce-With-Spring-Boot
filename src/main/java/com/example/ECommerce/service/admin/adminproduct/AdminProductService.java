package com.example.ECommerce.service.admin.adminproduct;

import java.util.List;

import com.example.ECommerce.dto.ProductDto;

import io.jsonwebtoken.io.IOException;

public interface AdminProductService {
	ProductDto addProduct(ProductDto productDto) throws IOException, java.io.IOException;
	List<ProductDto> getAllProductsByName(String name);
	List<ProductDto> getAllProducts();
	boolean deleteProduct(Long id);
}
