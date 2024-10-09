package com.example.ECommerce.service.customer.cart;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ECommerce.Enums.OrderStatus;
import com.example.ECommerce.dto.AddProductInCardDto;
import com.example.ECommerce.dto.CartItemsDto;
import com.example.ECommerce.dto.OrderDto;
import com.example.ECommerce.model.CartItems;
import com.example.ECommerce.model.Order;
import com.example.ECommerce.model.Product;
import com.example.ECommerce.model.User;
import com.example.ECommerce.repository.CartItemsRepository;
import com.example.ECommerce.repository.OrderRepository;
import com.example.ECommerce.repository.ProductRepository;
import com.example.ECommerce.repository.UserRepository;

@Service
public class CartServiceImpl implements CartService{
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartItemsRepository cartItemsRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public ResponseEntity<?> addProductToCart(AddProductInCardDto addProductInCardDto){
		Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCardDto.getUserId(), OrderStatus.Pending);
		Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId
				(addProductInCardDto.getProductId(),activeOrder.getId(),addProductInCardDto.getUserId());
		
		if(optionalCartItems.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}else {
			Optional<Product> optionalProduct = productRepository.findById(addProductInCardDto.getProductId());
			Optional<User> optionalUser = userRepository.findById(addProductInCardDto.getUserId());
			
			if(optionalProduct.isPresent() && optionalUser.isPresent()) {
				CartItems cart = new CartItems();
				cart.setProduct(optionalProduct.get());
				cart.setPrice(optionalProduct.get().getPrice());
				cart.setQuantity(1L);
				cart.setUser(optionalUser.get());
				cart.setOrder(activeOrder);
				
				CartItems updatedCart = cartItemsRepository.save(cart);
				
				activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cart.getPrice());
				activeOrder.setAmount(activeOrder.getAmount()+ cart.getPrice());
				activeOrder.getCartItems().add(cart);
				
				orderRepository.save(activeOrder);
				return  ResponseEntity.status(HttpStatus.CREATED).body(cart);
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
			}
		}
	}
	public OrderDto getCartByUserId(Long userId) {
		Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
		List<CartItemsDto> cartItemsDtoList = activeOrder.getCartItems().stream().map(CartItems::getCartDto).collect(Collectors.toList());
		OrderDto orderDto = new OrderDto();
		orderDto.setAmount(activeOrder.getAmount());
		orderDto.setId(activeOrder.getId());
		orderDto.setOrderStatus(activeOrder.getOrderStatus());
		orderDto.setDiscount(activeOrder.getDiscount());
		orderDto.setTotalAmount(activeOrder.getTotalAmount());
		orderDto.setCartItems(cartItemsDtoList);
		return orderDto;
	}
}
