package com.example.ECommerce.dto;

import lombok.Data;

@Data
public class SingUpRequest {
	private String email;
	private String password;
	private String name;
}
