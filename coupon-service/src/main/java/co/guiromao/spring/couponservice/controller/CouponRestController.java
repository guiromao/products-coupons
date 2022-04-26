package co.guiromao.spring.couponservice.controller;

import co.guiromao.spring.couponservice.model.Coupon;
import co.guiromao.spring.couponservice.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/coupons-api/coupons")
@CrossOrigin
public class CouponRestController {

    private CouponService couponService;

    @Autowired
    public CouponRestController(CouponService service) {
        couponService = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Coupon createCoupon(@RequestBody Coupon coupon) {
        return couponService.createCoupon(coupon);
    }

    @GetMapping("/{code}")
    //@PostAuthorize("returnObject.discount < 30")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Coupon findCoupon(@PathVariable("code") String code) {
        return couponService.getCouponWithCode(code);
    }

}
