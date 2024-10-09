package com.example.ECommerce.service.customer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ECommerce.dto.ProductDto;
import com.example.ECommerce.model.Product;
import com.example.ECommerce.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService{
	
	private final ProductRepository productRepository;
	
	public List<ProductDto> getAllProducts(){
		List<Product> products = productRepository.findAll();
		return products.stream().map(Product::getDto).collect(Collectors.toList());
	}
	public List<ProductDto> searchProductByTitle(String title){
		List<Product> products = productRepository.findAllByNameContaining(title);
		return products.stream().map(Product::getDto).collect(Collectors.toList());
	}
	
}
