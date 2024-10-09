package com.example.ECommerce.service.customer.cart;

import org.springframework.http.ResponseEntity;

import com.example.ECommerce.dto.AddProductInCardDto;
import com.example.ECommerce.dto.OrderDto;

public interface CartService {
	ResponseEntity<?> addProductToCart(AddProductInCardDto addProductInCardDto);
	OrderDto getCartByUserId(Long userId);
}
