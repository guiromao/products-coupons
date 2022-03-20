package co.guiromao.spring.couponservice.controller;

import co.guiromao.spring.couponservice.model.Coupon;
import co.guiromao.spring.couponservice.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CouponPagesController {

    private CouponService couponService;

    @Autowired
    public CouponPagesController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/showCreateCoupon")
    public String createCoupon() {
        return "createCoupon";
    }

    @PostMapping("/saveCoupon")
    public String saveCoupon(Coupon coupon) {
        couponService.createCoupon(coupon);

        return "createResponse";
    }

    @GetMapping("/showGetCoupon")
    public String showGetCoupon() {
        return "getCoupon";
    }

    @PostMapping("/getCoupon")
    public ModelAndView getCoupon(String code) {
        ModelAndView modelAndView = new ModelAndView("couponDetails");
        Coupon coupon = couponService.getCouponWithCode(code);
        modelAndView.addObject(coupon);

        return modelAndView;
    }

}
