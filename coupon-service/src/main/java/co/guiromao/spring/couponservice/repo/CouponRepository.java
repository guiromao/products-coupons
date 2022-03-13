package co.guiromao.spring.couponservice.repo;

import co.guiromao.spring.couponservice.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    Coupon findByCode(String code);

}
