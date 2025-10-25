package com.obi.bigpanda.Repository;

import com.obi.bigpanda.Entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<CouponEntity, Long> {

//    Optional<CouponEntity> findByParkIdAndIsActiveTrueAndExpiryDateAfter(Long parkId, LocalDateTime now);

    List<CouponEntity> findByParkIdAndIsActiveTrueAndExpiryDateAfter(Long parkId, LocalDateTime now);
}




//    List<CouponEntity> findByParkIdAndIsActiveTrueAndExpiryDateAfter(Long parkId, LocalDateTime now);
//
//
//    List<CouponEntity> findByParkIdAndIsActiveTrueAndExpiryDateAfter(Long parkId, LocalDateTime now);
//
//    Optional<CouponEntity> findByIdAndDeletedFalse(Long id);
//
//    List<CouponEntity> findByDeletedFalse(); // all active (not deleted) coupons
//
//    List<CouponEntity> findByDeletedTrue(); // all deleted coupons