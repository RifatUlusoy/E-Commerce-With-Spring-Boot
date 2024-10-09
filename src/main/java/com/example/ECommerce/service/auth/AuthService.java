package com.example.ECommerce.service.auth;

import com.example.ECommerce.dto.SingUpRequest;
import com.example.ECommerce.dto.UserDto;

public interface AuthService {
	
	UserDto createUser(SingUpRequest singupRequest);

	boolean hasUserWithEmail(String email);
}
