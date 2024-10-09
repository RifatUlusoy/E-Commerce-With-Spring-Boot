package com.example.ECommerce.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ECommerce.Enums.OrderStatus;
import com.example.ECommerce.Enums.UserRole;
import com.example.ECommerce.dto.SingUpRequest;
import com.example.ECommerce.dto.UserDto;
import com.example.ECommerce.model.Order;
import com.example.ECommerce.model.User;
import com.example.ECommerce.repository.OrderRepository;
import com.example.ECommerce.repository.UserRepository;

import jakarta.annotation.PostConstruct;


@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private OrderRepository orderRepository;
	public UserDto createUser(SingUpRequest singUpRequest) {
		User user = new User();
		user.setEmail(singUpRequest.getEmail());
		user.setName(singUpRequest.getName());
		user.setPassword(bCryptPasswordEncoder.encode(singUpRequest.getPassword()));
		user.setRole(UserRole.CUSTOMER);
		
		User createdUser = userRepository.save(user);
		
		Order order = new Order();
		order.setAmount(0L);
		order.setTotalAmount(0L);
		order.setDiscount(0L);
		order.setUser(createdUser);
		order.setOrderStatus(OrderStatus.Pending);
		orderRepository.save(order);
		
		UserDto userDto = new UserDto();
		userDto.setId(createdUser.getId());
		return userDto;
	}
	public boolean hasUserWithEmail(String email) {
		return userRepository.findFirstByEmail(email).isPresent();
	}
	@PostConstruct
	public void createAdminAccount() {
		User adminAccount = userRepository.findByRole(UserRole.ADMIN);
		if(null == adminAccount) {
			User user = new User();
			user.setEmail("admin@test.com");
			user.setName("admin");
			user.setRole(UserRole.ADMIN);;
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}
}
