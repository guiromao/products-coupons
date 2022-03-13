package co.guiromao.spring.couponservice.service;

import co.guiromao.spring.couponservice.model.Coupon;
import co.guiromao.spring.couponservice.repo.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponService {

    private CouponRepository couponRepository;

    @Autowired
    public CouponService(CouponRepository repo) {
        couponRepository = repo;
    }

    public Coupon getCouponWithCode(String code) {
        return couponRepository.findByCode(code);
    }

    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.saveAndFlush(coupon);
    }

}
