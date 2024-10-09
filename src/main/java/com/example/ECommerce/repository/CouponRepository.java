package com.example.ECommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ECommerce.model.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long>{
	
	boolean existsByCode(String code);
}
