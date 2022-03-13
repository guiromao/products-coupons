package co.guiromao.spring.couponservice.controller;

import co.guiromao.spring.couponservice.model.Coupon;
import co.guiromao.spring.couponservice.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/coupons-api/coupons")
public class CouponController {

    private CouponService couponService;

    @Autowired
    public CouponController(CouponService service) {
        couponService = service;
    }

    @PostMapping
    public Coupon createCoupon(@RequestBody Coupon coupon) {
        return couponService.createCoupon(coupon);
    }

    @GetMapping("/{code}")
    public Coupon findCoupon(@PathVariable("code") String code) {
        return couponService.getCouponWithCode(code);
    }

}
