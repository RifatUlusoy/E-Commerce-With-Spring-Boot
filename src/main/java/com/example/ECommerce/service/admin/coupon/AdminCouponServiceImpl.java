package com.example.ECommerce.service.admin.coupon;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ECommerce.exception.ValidationException;
import com.example.ECommerce.model.Coupon;
import com.example.ECommerce.repository.CouponRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminCouponServiceImpl implements AdminCouponService{
	
	private final CouponRepository couponRepository;
	
	public Coupon createCoupon(Coupon coupon) {
		if(couponRepository.existsByCode(coupon.getCode())) {
			throw new ValidationException("Coupon code already exists");
		}
		return couponRepository.save(coupon);
	}

	@Override
	public List<Coupon> getAllCoupons() {
		return couponRepository.findAll();
	}
}
