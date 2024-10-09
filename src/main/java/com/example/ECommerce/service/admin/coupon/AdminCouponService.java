package com.example.ECommerce.service.admin.coupon;

import java.util.List;

import com.example.ECommerce.model.Coupon;

public interface AdminCouponService {
	Coupon createCoupon(Coupon coupon);
	List<Coupon> getAllCoupons();
}
