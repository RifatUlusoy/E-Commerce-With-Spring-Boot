package com.example.ECommerce.controller.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ECommerce.exception.ValidationException;
import com.example.ECommerce.model.Coupon;
import com.example.ECommerce.service.admin.coupon.AdminCouponService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/coupons")
@RequiredArgsConstructor
public class AdminCouponController {
	private final AdminCouponService adminCouponService;
	
	@PostMapping
	public ResponseEntity<?> createCoupon(@RequestBody Coupon coupon){
		try {
			Coupon createdCoupon = adminCouponService.createCoupon(coupon);
			return ResponseEntity.ok(createdCoupon);
		} catch (ValidationException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}	
	@GetMapping
	public ResponseEntity<List<Coupon>> getAllCoupons(){
		return ResponseEntity.ok(adminCouponService.getAllCoupons());
	}
}
