package com.example.ECommerce.controller.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ECommerce.dto.AddProductInCardDto;
import com.example.ECommerce.dto.OrderDto;
import com.example.ECommerce.service.customer.cart.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CartController {
	private final CartService cartService;
	
	
	@PostMapping("/cart")
	public ResponseEntity<?> addProductToCart(@RequestBody AddProductInCardDto addProductInCardDto){
		return cartService.addProductToCart(addProductInCardDto);
	}
	@GetMapping("/cart/{userId}")
	public ResponseEntity<?> getCartByUserId(@PathVariable Long userId){
		OrderDto orderDto = cartService.getCartByUserId(userId);
		return ResponseEntity.status(HttpStatus.OK).body(orderDto);
	}
}
