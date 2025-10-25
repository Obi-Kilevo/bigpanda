package com.obi.bigpanda.Controller;

import com.obi.bigpanda.Entity.AdminEntity;
import com.obi.bigpanda.Entity.CouponEntity;
import com.obi.bigpanda.Repository.AdminRepository;
import com.obi.bigpanda.Repository.BookingRepository;
import com.obi.bigpanda.Repository.CouponRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/hi")
public class mamboController {


    private final BookingRepository bookingRepository;
    private final AdminRepository adminRepository;
    private final CouponRepository couponRepository;

    public mamboController(BookingRepository bookingRepository,
                           AdminRepository adminRepository,  CouponRepository couponRepository) {
        this.bookingRepository = bookingRepository;
        this.adminRepository = adminRepository;
        this.couponRepository = couponRepository;
    }


    @GetMapping("/ngorongoro")
    public String showNgorongoro(Model model) {
        Long parkId = 14L;
        AdminEntity park = adminRepository.findById(parkId).orElse(null);

        if (park != null) {
            if (park.getDeleted()) {
                model.addAttribute("price", "Closed");
                model.addAttribute("name", park.getName());
            } else {
                // CHECK FOR ACTIVE COUPONS (now returns list)
                List<CouponEntity> activeCoupons = couponRepository
                        .findByParkIdAndIsActiveTrueAndExpiryDateAfter(parkId, LocalDateTime.now());

                if (!activeCoupons.isEmpty()) {
                    // Use the first coupon, or implement logic to choose the best one
                    CouponEntity coupon = activeCoupons.get(0);
                    BigDecimal parkPrice = BigDecimal.valueOf(park.getPrice());
                    BigDecimal discountPercent = coupon.getDiscountPercent();
                    BigDecimal discountAmount = parkPrice.multiply(
                            discountPercent.divide(BigDecimal.valueOf(100)));
                    BigDecimal finalPrice = parkPrice.subtract(discountAmount);

                    model.addAttribute("originalPrice", park.getPrice());
                    model.addAttribute("finalPrice", finalPrice.doubleValue());
                    model.addAttribute("discountPercent", discountPercent.doubleValue());
                    model.addAttribute("hasCoupon", true);
                } else {
                    model.addAttribute("finalPrice", park.getPrice());
                    model.addAttribute("hasCoupon", false);
                }

                model.addAttribute("price", park.getPrice());
                model.addAttribute("name", park.getName());
            }
        } else {
            model.addAttribute("price", "Thanks! Ngorongoro is closed for a while");
            model.addAttribute("name", "Ngorongoro National Park");
            model.addAttribute("hasCoupon", false);
        }

        return "test/ngorongoro";
    }
//    @GetMapping("/ngorongoro")
//    public String showNgorongoro(Model model) {
//        Long parkId = 14L;
//        AdminEntity park = adminRepository.findById(parkId).orElse(null);
//
//        if (park != null) {
//            if (park.getDeleted()) {
//                model.addAttribute("price", "Closed");
//                model.addAttribute("name", park.getName());
//            } else {
//                // CHECK FOR ACTIVE COUPON
//                Optional<CouponEntity> activeCoupon = couponRepository
//                        .findByParkIdAndIsActiveTrueAndExpiryDateAfter(parkId, LocalDateTime.now());
//
//                if (activeCoupon.isPresent()) {
//                    CouponEntity coupon = activeCoupon.get();
//                    BigDecimal parkPrice = BigDecimal.valueOf(park.getPrice());
//                    BigDecimal discountPercent = coupon.getDiscountPercent();
//                    BigDecimal discountAmount = parkPrice.multiply(
//                            discountPercent.divide(BigDecimal.valueOf(100)));
//                    BigDecimal finalPrice = parkPrice.subtract(discountAmount);
//
//                    // ADD COUPON INFO TO MODEL
//                    model.addAttribute("originalPrice", park.getPrice());
//                    model.addAttribute("finalPrice", finalPrice.doubleValue());
//                    model.addAttribute("discountPercent", discountPercent.doubleValue());
//                    model.addAttribute("hasCoupon", true);
//                } else {
//                    model.addAttribute("finalPrice", park.getPrice());
//                    model.addAttribute("hasCoupon", false);
//                }
//
//                model.addAttribute("price", park.getPrice()); // Keep original for backward compatibility
//                model.addAttribute("name", park.getName());
//            }
//        } else {
//            model.addAttribute("price", "Thanks! Ngorongoro is closed for a while");
//            model.addAttribute("name", "Ngorongoro National Park");
//            model.addAttribute("hasCoupon", false);
//        }
//
//        return "test/ngorongoro";
//    }



//    @GetMapping("/kikuletwa")
//    public String showKikuletwa(Model model) {
//        Long parkId = 23L;
//        AdminEntity park = adminRepository.findById(parkId).orElse(null);
//
//        if (park != null) {
//            if (park.getDeleted()) {
//                model.addAttribute("price", "Closed");
//                model.addAttribute("name", park.getName());
//            } else {
//                Optional<CouponEntity> activeCoupon = couponRepository
//                        .findByParkIdAndIsActiveTrueAndExpiryDateAfter(parkId, LocalDateTime.now());
//
//                if (activeCoupon.isPresent()) {
//                    CouponEntity coupon = activeCoupon.get();
//                    BigDecimal parkPrice = BigDecimal.valueOf(park.getPrice());
//                    BigDecimal discountPercent = coupon.getDiscountPercent();
//                    BigDecimal discountAmount = parkPrice.multiply(
//                            discountPercent.divide(BigDecimal.valueOf(100)));
//                    BigDecimal finalPrice = parkPrice.subtract(discountAmount);
//
//                    model.addAttribute("originalPrice", park.getPrice());
//                    model.addAttribute("finalPrice", finalPrice.doubleValue());
//                    model.addAttribute("discountPercent", discountPercent.doubleValue());
//                    model.addAttribute("hasCoupon", true);
//                } else {
//                    model.addAttribute("finalPrice", park.getPrice());
//                    model.addAttribute("hasCoupon", false);
//                }
//
//                model.addAttribute("price", park.getPrice());
//                model.addAttribute("name", park.getName());
//            }
//        } else {
//            model.addAttribute("price", "Thanks! Kikuletwa is closed for a while");
//            model.addAttribute("name", "Kikuletwa National Park");
//            model.addAttribute("hasCoupon", false);
//        }
//
//        return "test/kikuletwa";
//    }

    @GetMapping("/kikuletwa")
    public String showKikuletwa(Model model) {
        Long parkId = 23L;
        AdminEntity park = adminRepository.findById(parkId).orElse(null);

        if (park != null) {
            if (park.getDeleted()) {
                model.addAttribute("price", "Closed");
                model.addAttribute("name", park.getName());
            } else {
                // CHANGE: Now returns List instead of Optional
                List<CouponEntity> activeCoupons = couponRepository
                        .findByParkIdAndIsActiveTrueAndExpiryDateAfter(parkId, LocalDateTime.now());

                if (!activeCoupons.isEmpty()) {
                    // Use the first active coupon (or choose based on business logic)
                    CouponEntity coupon = activeCoupons.get(0);
                    BigDecimal parkPrice = BigDecimal.valueOf(park.getPrice());
                    BigDecimal discountPercent = coupon.getDiscountPercent();
                    BigDecimal discountAmount = parkPrice.multiply(
                            discountPercent.divide(BigDecimal.valueOf(100)));
                    BigDecimal finalPrice = parkPrice.subtract(discountAmount);

                    model.addAttribute("originalPrice", park.getPrice());
                    model.addAttribute("finalPrice", finalPrice.doubleValue());
                    model.addAttribute("discountPercent", discountPercent.doubleValue());
                    model.addAttribute("hasCoupon", true);
                } else {
                    model.addAttribute("finalPrice", park.getPrice());
                    model.addAttribute("hasCoupon", false);
                }

                model.addAttribute("price", park.getPrice());
                model.addAttribute("name", park.getName());
            }
        } else {
            model.addAttribute("price", "Thanks! Kikuletwa is closed for a while");
            model.addAttribute("name", "Kikuletwa National Park");
            model.addAttribute("hasCoupon", false);
        }

        return "test/kikuletwa";
    }

//    @GetMapping("/kikuletwa")
//    public String showKikuletwa(Model model) {
//        // Fixed park ID from your database
//        Long parkId = 23L;
//
//        AdminEntity park = adminRepository.findById(parkId).orElse(null);
//
//        if (park != null) {
//            // If the admin marks it deleted, show "Closed"
//            model.addAttribute("price", park.getDeleted() ? "Closed" : park.getPrice());
//            // Always show the *latest updated name*
//            model.addAttribute("name", park.getName());
//        } else {
//            // If not found (maybe deleted permanently)
//            model.addAttribute("price", "Thanks! Kikuletwa is closed for a while");
//            model.addAttribute("name", "Kikuletwa National Park");
//        }
//
//        return "test/kikuletwa";
//    }


    @GetMapping("/mkomazi")
    public String showMkomazi(Model model) {
        Long parkId = 21L;
        AdminEntity park = adminRepository.findById(parkId).orElse(null);

        if (park != null) {
            if (park.getDeleted()) {
                model.addAttribute("price", "Closed");
                model.addAttribute("name", park.getName());
            } else {
                // CHANGE: Now returns List instead of Optional
                List<CouponEntity> activeCoupons = couponRepository
                        .findByParkIdAndIsActiveTrueAndExpiryDateAfter(parkId, LocalDateTime.now());

                if (!activeCoupons.isEmpty()) {
                    // Use the first active coupon (or choose based on business logic)
                    CouponEntity coupon = activeCoupons.get(0);
                    BigDecimal parkPrice = BigDecimal.valueOf(park.getPrice());
                    BigDecimal discountPercent = coupon.getDiscountPercent();
                    BigDecimal discountAmount = parkPrice.multiply(
                            discountPercent.divide(BigDecimal.valueOf(100)));
                    BigDecimal finalPrice = parkPrice.subtract(discountAmount);

                    model.addAttribute("originalPrice", park.getPrice());
                    model.addAttribute("finalPrice", finalPrice.doubleValue());
                    model.addAttribute("discountPercent", discountPercent.doubleValue());
                    model.addAttribute("hasCoupon", true);
                } else {
                    model.addAttribute("finalPrice", park.getPrice());
                    model.addAttribute("hasCoupon", false);
                }

                model.addAttribute("price", park.getPrice());
                model.addAttribute("name", park.getName());
            }
        } else {
            model.addAttribute("price", "Thanks! Mkomazi is closed for a while");
            model.addAttribute("name", "Mkomazi National Park");
            model.addAttribute("hasCoupon", false);
        }

        return "test/mkomazi";
    }
//    @GetMapping("/mkomazi")
//    public String showMkomazi(Model model) {
//        Long parkId = 21L;
//        AdminEntity park = adminRepository.findById(parkId).orElse(null);
//
//        if (park != null) {
//            if (park.getDeleted()) {
//                model.addAttribute("price", "Closed");
//                model.addAttribute("name", park.getName());
//            } else {
//                Optional<CouponEntity> activeCoupon = couponRepository
//                        .findByParkIdAndIsActiveTrueAndExpiryDateAfter(parkId, LocalDateTime.now());
//
//                if (activeCoupon.isPresent()) {
//                    CouponEntity coupon = activeCoupon.get();
//                    BigDecimal parkPrice = BigDecimal.valueOf(park.getPrice());
//                    BigDecimal discountPercent = coupon.getDiscountPercent();
//                    BigDecimal discountAmount = parkPrice.multiply(
//                            discountPercent.divide(BigDecimal.valueOf(100)));
//                    BigDecimal finalPrice = parkPrice.subtract(discountAmount);
//
//                    model.addAttribute("originalPrice", park.getPrice());
//                    model.addAttribute("finalPrice", finalPrice.doubleValue());
//                    model.addAttribute("discountPercent", discountPercent.doubleValue());
//                    model.addAttribute("hasCoupon", true);
//                } else {
//                    model.addAttribute("finalPrice", park.getPrice());
//                    model.addAttribute("hasCoupon", false);
//                }
//
//                model.addAttribute("price", park.getPrice());
//                model.addAttribute("name", park.getName());
//            }
//        } else {
//            model.addAttribute("price", "Thanks! Mkomazi is closed for a while");
//            model.addAttribute("name", "Mkomazi National Park");
//            model.addAttribute("hasCoupon", false);
//        }
//
//        return "test/mkomazi";
//    }


//    @GetMapping("/mkomazi")
//    public String showMkomazi(Model model) {
//        // Fixed park ID from your database
//        Long parkId = 21L;
//
//        AdminEntity park = adminRepository.findById(parkId).orElse(null);
//
//        if (park != null) {
//            // If the admin marks it deleted, show "Closed"
//            model.addAttribute("price", park.getDeleted() ? "Closed" : park.getPrice());
//            // Always show the *latest updated name*
//            model.addAttribute("name", park.getName());
//        } else {
//            // If not found (maybe deleted permanently)
//            model.addAttribute("price", "Thanks! Mkomazi is closed for a while");
//            model.addAttribute("name", "Mkomazi National Park");
//        }
//
//        return "test/mkomazi";
//    }


    @GetMapping("/kilimanjaro")
    public String showKilimanjaro(Model model) {
        Long parkId = 20L;
        AdminEntity park = adminRepository.findById(parkId).orElse(null);

        if (park != null) {
            if (park.getDeleted()) {
                model.addAttribute("price", "Closed");
                model.addAttribute("name", park.getName());
            } else {
                // CHANGE: Now returns List instead of Optional
                List<CouponEntity> activeCoupons = couponRepository
                        .findByParkIdAndIsActiveTrueAndExpiryDateAfter(parkId, LocalDateTime.now());

                if (!activeCoupons.isEmpty()) {
                    // Use the first active coupon (or choose based on business logic)
                    CouponEntity coupon = activeCoupons.get(0);
                    BigDecimal parkPrice = BigDecimal.valueOf(park.getPrice());
                    BigDecimal discountPercent = coupon.getDiscountPercent();
                    BigDecimal discountAmount = parkPrice.multiply(
                            discountPercent.divide(BigDecimal.valueOf(100)));
                    BigDecimal finalPrice = parkPrice.subtract(discountAmount);

                    model.addAttribute("originalPrice", park.getPrice());
                    model.addAttribute("finalPrice", finalPrice.doubleValue());
                    model.addAttribute("discountPercent", discountPercent.doubleValue());
                    model.addAttribute("hasCoupon", true);
                } else {
                    model.addAttribute("finalPrice", park.getPrice());
                    model.addAttribute("hasCoupon", false);
                }

                model.addAttribute("price", park.getPrice());
                model.addAttribute("name", park.getName());
            }
        } else {
            model.addAttribute("price", "Thanks! Kilimanjaro is closed for a while");
            model.addAttribute("name", "Mount Kilimanjaro");
            model.addAttribute("hasCoupon", false);
        }

        return "test/kilimanjaro";
    }

//
//    @GetMapping("/kilimanjaro")
//    public String showKilimanjaro(Model model) {
//        Long parkId = 20L;
//        AdminEntity park = adminRepository.findById(parkId).orElse(null);
//
//        if (park != null) {
//            if (park.getDeleted()) {
//                model.addAttribute("price", "Closed");
//                model.addAttribute("name", park.getName());
//            } else {
//                Optional<CouponEntity> activeCoupon = couponRepository
//                        .findByParkIdAndIsActiveTrueAndExpiryDateAfter(parkId, LocalDateTime.now());
//
//                if (activeCoupon.isPresent()) {
//                    CouponEntity coupon = activeCoupon.get();
//                    BigDecimal parkPrice = BigDecimal.valueOf(park.getPrice());
//                    BigDecimal discountPercent = coupon.getDiscountPercent();
//                    BigDecimal discountAmount = parkPrice.multiply(
//                            discountPercent.divide(BigDecimal.valueOf(100)));
//                    BigDecimal finalPrice = parkPrice.subtract(discountAmount);
//
//                    model.addAttribute("originalPrice", park.getPrice());
//                    model.addAttribute("finalPrice", finalPrice.doubleValue());
//                    model.addAttribute("discountPercent", discountPercent.doubleValue());
//                    model.addAttribute("hasCoupon", true);
//                } else {
//                    model.addAttribute("finalPrice", park.getPrice());
//                    model.addAttribute("hasCoupon", false);
//                }
//
//                model.addAttribute("price", park.getPrice());
//                model.addAttribute("name", park.getName());
//            }
//        } else {
//            model.addAttribute("price", "Thanks! Kilimanjaro is closed for a while");
//            model.addAttribute("name", "Mount Kilimanjaro");
//            model.addAttribute("hasCoupon", false);
//        }
//
//        return "test/kilimanjaro";
//    }


//
//    @GetMapping("/kilimanjaro")
//    public String showKilimanjaro(Model model) {
//        // Fixed park ID from your database
//        Long parkId = 20L;
//
//        AdminEntity park = adminRepository.findById(parkId).orElse(null);
//
//        if (park != null) {
//            // If the admin marks it deleted, show "Closed"
//            model.addAttribute("price", park.getDeleted() ? "Closed" : park.getPrice());
//            // Always show the *latest updated name*
//            model.addAttribute("name", park.getName());
//        } else {
//            // If not found (maybe deleted permanently)
//            model.addAttribute("price", "Thanks! Kilimanjaro is closed for a while");
//            model.addAttribute("name", "Mount Kilimanjaro");
//        }
//
//        return "test/kilimanjaro";
//    }


    @GetMapping("/manyara")
    public String showManyara(Model model) {
        Long parkId = 15L;
        AdminEntity park = adminRepository.findById(parkId).orElse(null);

        if (park != null) {
            if (park.getDeleted()) {
                model.addAttribute("price", "Closed");
                model.addAttribute("name", park.getName());
            } else {
                // CHANGE: Now returns List instead of Optional
                List<CouponEntity> activeCoupons = couponRepository
                        .findByParkIdAndIsActiveTrueAndExpiryDateAfter(parkId, LocalDateTime.now());

                if (!activeCoupons.isEmpty()) {
                    // Use the first active coupon (or choose based on business logic)
                    CouponEntity coupon = activeCoupons.get(0);
                    BigDecimal parkPrice = BigDecimal.valueOf(park.getPrice());
                    BigDecimal discountPercent = coupon.getDiscountPercent();
                    BigDecimal discountAmount = parkPrice.multiply(
                            discountPercent.divide(BigDecimal.valueOf(100)));
                    BigDecimal finalPrice = parkPrice.subtract(discountAmount);

                    model.addAttribute("originalPrice", park.getPrice());
                    model.addAttribute("finalPrice", finalPrice.doubleValue());
                    model.addAttribute("discountPercent", discountPercent.doubleValue());
                    model.addAttribute("hasCoupon", true);
                } else {
                    model.addAttribute("finalPrice", park.getPrice());
                    model.addAttribute("hasCoupon", false);
                }

                model.addAttribute("price", park.getPrice());
                model.addAttribute("name", park.getName());
            }
        } else {
            model.addAttribute("price", "Thanks! Manyara National Park is closed for a while");
            model.addAttribute("name", "Manyara National Park");
            model.addAttribute("hasCoupon", false);
        }

        return "test/manyara";
    }

//    @GetMapping("/manyara")
//    public String showManyara(Model model) {
//        Long parkId = 15L;
//        AdminEntity park = adminRepository.findById(parkId).orElse(null);
//
//        if (park != null) {
//            if (park.getDeleted()) {
//                model.addAttribute("price", "Closed");
//                model.addAttribute("name", park.getName());
//            } else {
//                Optional<CouponEntity> activeCoupon = couponRepository
//                        .findByParkIdAndIsActiveTrueAndExpiryDateAfter(parkId, LocalDateTime.now());
//
//                if (activeCoupon.isPresent()) {
//                    CouponEntity coupon = activeCoupon.get();
//                    BigDecimal parkPrice = BigDecimal.valueOf(park.getPrice());
//                    BigDecimal discountPercent = coupon.getDiscountPercent();
//                    BigDecimal discountAmount = parkPrice.multiply(
//                            discountPercent.divide(BigDecimal.valueOf(100)));
//                    BigDecimal finalPrice = parkPrice.subtract(discountAmount);
//
//                    model.addAttribute("originalPrice", park.getPrice());
//                    model.addAttribute("finalPrice", finalPrice.doubleValue());
//                    model.addAttribute("discountPercent", discountPercent.doubleValue());
//                    model.addAttribute("hasCoupon", true);
//                } else {
//                    model.addAttribute("finalPrice", park.getPrice());
//                    model.addAttribute("hasCoupon", false);
//                }
//
//                model.addAttribute("price", park.getPrice());
//                model.addAttribute("name", park.getName());
//            }
//        } else {
//            model.addAttribute("price", "Thanks! Manyara National Park is closed for a while");
//            model.addAttribute("name", "Manyara National Park");
//            model.addAttribute("hasCoupon", false);
//        }
//
//        return "test/manyara";
//    }




//
//    @GetMapping("/olduvai")
//    public String showOlduvai(Model model) {
//        Long parkId = 19L;
//        AdminEntity park = adminRepository.findById(parkId).orElse(null);
//
//        if (park != null) {
//            if (park.getDeleted()) {
//                model.addAttribute("price", "Closed");
//                model.addAttribute("name", park.getName());
//            } else {
//                Optional<CouponEntity> activeCoupon = couponRepository
//                        .findByParkIdAndIsActiveTrueAndExpiryDateAfter(parkId, LocalDateTime.now());
//
//                if (activeCoupon.isPresent()) {
//                    CouponEntity coupon = activeCoupon.get();
//                    BigDecimal parkPrice = BigDecimal.valueOf(park.getPrice());
//                    BigDecimal discountPercent = coupon.getDiscountPercent();
//                    BigDecimal discountAmount = parkPrice.multiply(
//                            discountPercent.divide(BigDecimal.valueOf(100)));
//                    BigDecimal finalPrice = parkPrice.subtract(discountAmount);
//
//                    model.addAttribute("originalPrice", park.getPrice());
//                    model.addAttribute("finalPrice", finalPrice.doubleValue());
//                    model.addAttribute("discountPercent", discountPercent.doubleValue());
//                    model.addAttribute("hasCoupon", true);
//                } else {
//                    model.addAttribute("finalPrice", park.getPrice());
//                    model.addAttribute("hasCoupon", false);
//                }
//
//                model.addAttribute("price", park.getPrice());
//                model.addAttribute("name", park.getName());
//            }
//        } else {
//            model.addAttribute("price", "Thanks! Olduvai Gorge is closed for a while");
//            model.addAttribute("name", "Olduvai Gorge");
//            model.addAttribute("hasCoupon", false);
//        }
//
//        return "test/olduvai";
//    }
@GetMapping("/olduvai")
public String showOlduvai(Model model) {
    Long parkId = 19L;
    AdminEntity park = adminRepository.findById(parkId).orElse(null);

    if (park != null) {
        if (park.getDeleted()) {
            model.addAttribute("price", "Closed");
            model.addAttribute("name", park.getName());
        } else {
            // CHANGE: Now returns List instead of Optional
            List<CouponEntity> activeCoupons = couponRepository
                    .findByParkIdAndIsActiveTrueAndExpiryDateAfter(parkId, LocalDateTime.now());

            if (!activeCoupons.isEmpty()) {
                // Use the first active coupon (or choose based on business logic)
                CouponEntity coupon = activeCoupons.get(0);
                BigDecimal parkPrice = BigDecimal.valueOf(park.getPrice());
                BigDecimal discountPercent = coupon.getDiscountPercent();
                BigDecimal discountAmount = parkPrice.multiply(
                        discountPercent.divide(BigDecimal.valueOf(100)));
                BigDecimal finalPrice = parkPrice.subtract(discountAmount);

                model.addAttribute("originalPrice", park.getPrice());
                model.addAttribute("finalPrice", finalPrice.doubleValue());
                model.addAttribute("discountPercent", discountPercent.doubleValue());
                model.addAttribute("hasCoupon", true);
            } else {
                model.addAttribute("finalPrice", park.getPrice());
                model.addAttribute("hasCoupon", false);
            }

            model.addAttribute("price", park.getPrice());
            model.addAttribute("name", park.getName());
        }
    } else {
        model.addAttribute("price", "Thanks! Olduvai Gorge is closed for a while");
        model.addAttribute("name", "Olduvai Gorge");
        model.addAttribute("hasCoupon", false);
    }

    return "test/olduvai";
}

//    @GetMapping("/olduvai")
//    public String showOlduvai(Model model) {
//        // Fixed park ID from your database
//        Long parkId = 19L;
//
//        AdminEntity park = adminRepository.findById(parkId).orElse(null);
//
//        if (park != null) {
//            // If the admin marks it deleted, show "Closed"
//            model.addAttribute("price", park.getDeleted() ? "Closed" : park.getPrice());
//            // Always show the *latest updated name*
//            model.addAttribute("name", park.getName());
//        } else {
//            // If not found (maybe deleted permanently)
//            model.addAttribute("price", "Thanks! Olduvai Gorgeis closed for a while");
//            model.addAttribute("name", "Olduvai Gorge");
//        }
//
//        return "test/olduvai";
//    }



//
//    @GetMapping("/arusha")
//    public String showArusha(Model model) {
//        Long parkId = 12L;
//        AdminEntity park = adminRepository.findById(parkId).orElse(null);
//
//        if (park != null) {
//            if (park.getDeleted()) {
//                model.addAttribute("price", "Closed");
//                model.addAttribute("name", park.getName());
//            } else {
//                Optional<CouponEntity> activeCoupon = couponRepository
//                        .findByParkIdAndIsActiveTrueAndExpiryDateAfter(parkId, LocalDateTime.now());
//
//                if (activeCoupon.isPresent()) {
//                    CouponEntity coupon = activeCoupon.get();
//                    BigDecimal parkPrice = BigDecimal.valueOf(park.getPrice());
//                    BigDecimal discountPercent = coupon.getDiscountPercent();
//                    BigDecimal discountAmount = parkPrice.multiply(
//                            discountPercent.divide(BigDecimal.valueOf(100)));
//                    BigDecimal finalPrice = parkPrice.subtract(discountAmount);
//
//                    model.addAttribute("originalPrice", park.getPrice());
//                    model.addAttribute("finalPrice", finalPrice.doubleValue());
//                    model.addAttribute("discountPercent", discountPercent.doubleValue());
//                    model.addAttribute("hasCoupon", true);
//                } else {
//                    model.addAttribute("finalPrice", park.getPrice());
//                    model.addAttribute("hasCoupon", false);
//                }
//
//                model.addAttribute("price", park.getPrice());
//                model.addAttribute("name", park.getName());
//            }
//        } else {
//            model.addAttribute("price", "Thanks! Arusha National Park is closed for a while");
//            model.addAttribute("name", "Arusha National Park");
//            model.addAttribute("hasCoupon", false);
//        }
//
//        return "test/arusha";
//    }
//

    @GetMapping("/arusha")
    public String showArusha(Model model) {
        Long parkId = 12L;
        AdminEntity park = adminRepository.findById(parkId).orElse(null);

        if (park != null) {
            if (park.getDeleted()) {
                model.addAttribute("price", "Closed");
                model.addAttribute("name", park.getName());
            } else {
                // CHANGE: Now returns List instead of Optional
                List<CouponEntity> activeCoupons = couponRepository
                        .findByParkIdAndIsActiveTrueAndExpiryDateAfter(parkId, LocalDateTime.now());

                if (!activeCoupons.isEmpty()) {
                    // Use the first active coupon (or choose based on business logic)
                    CouponEntity coupon = activeCoupons.get(0);
                    BigDecimal parkPrice = BigDecimal.valueOf(park.getPrice());
                    BigDecimal discountPercent = coupon.getDiscountPercent();
                    BigDecimal discountAmount = parkPrice.multiply(
                            discountPercent.divide(BigDecimal.valueOf(100)));
                    BigDecimal finalPrice = parkPrice.subtract(discountAmount);

                    model.addAttribute("originalPrice", park.getPrice());
                    model.addAttribute("finalPrice", finalPrice.doubleValue());
                    model.addAttribute("discountPercent", discountPercent.doubleValue());
                    model.addAttribute("hasCoupon", true);
                } else {
                    model.addAttribute("finalPrice", park.getPrice());
                    model.addAttribute("hasCoupon", false);
                }

                model.addAttribute("price", park.getPrice());
                model.addAttribute("name", park.getName());
            }
        } else {
            model.addAttribute("price", "Thanks! Arusha National Park is closed for a while");
            model.addAttribute("name", "Arusha National Park");
            model.addAttribute("hasCoupon", false);
        }

        return "test/arusha";
    }


//
//    @GetMapping("/tarangire")
//    public String showTarangire(Model model) {
//        Long parkId = 7L;
//        AdminEntity park = adminRepository.findById(parkId).orElse(null);
//
//        if (park != null) {
//            if (park.getDeleted()) {
//                model.addAttribute("price", "Closed");
//                model.addAttribute("name", park.getName());
//            } else {
//                Optional<CouponEntity> activeCoupon = couponRepository
//                        .findByParkIdAndIsActiveTrueAndExpiryDateAfter(parkId, LocalDateTime.now());
//
//                if (activeCoupon.isPresent()) {
//                    CouponEntity coupon = activeCoupon.get();
//                    BigDecimal parkPrice = BigDecimal.valueOf(park.getPrice());
//                    BigDecimal discountPercent = coupon.getDiscountPercent();
//                    BigDecimal discountAmount = parkPrice.multiply(
//                            discountPercent.divide(BigDecimal.valueOf(100)));
//                    BigDecimal finalPrice = parkPrice.subtract(discountAmount);
//
//                    model.addAttribute("originalPrice", park.getPrice());
//                    model.addAttribute("finalPrice", finalPrice.doubleValue());
//                    model.addAttribute("discountPercent", discountPercent.doubleValue());
//                    model.addAttribute("hasCoupon", true);
//                } else {
//                    model.addAttribute("finalPrice", park.getPrice());
//                    model.addAttribute("hasCoupon", false);
//                }
//
//                model.addAttribute("price", park.getPrice());
//                model.addAttribute("name", park.getName());
//            }
//        } else {
//            model.addAttribute("price", "Thanks! Tarangire National Park is closed for a while");
//            model.addAttribute("name", "Tarangire National Park");
//            model.addAttribute("hasCoupon", false);
//        }
//
//        return "test/tarangire";
//    }


    @GetMapping("/tarangire")
    public String showTarangire(Model model) {
        Long parkId = 7L;
        AdminEntity park = adminRepository.findById(parkId).orElse(null);

        if (park != null) {
            if (park.getDeleted()) {
                model.addAttribute("price", "Closed");
                model.addAttribute("name", park.getName());
            } else {
                // CHANGE: Now returns List instead of Optional
                List<CouponEntity> activeCoupons = couponRepository
                        .findByParkIdAndIsActiveTrueAndExpiryDateAfter(parkId, LocalDateTime.now());

                if (!activeCoupons.isEmpty()) {
                    // Use the first active coupon (or choose based on business logic)
                    CouponEntity coupon = activeCoupons.get(0);
                    BigDecimal parkPrice = BigDecimal.valueOf(park.getPrice());
                    BigDecimal discountPercent = coupon.getDiscountPercent();
                    BigDecimal discountAmount = parkPrice.multiply(
                            discountPercent.divide(BigDecimal.valueOf(100)));
                    BigDecimal finalPrice = parkPrice.subtract(discountAmount);

                    model.addAttribute("originalPrice", park.getPrice());
                    model.addAttribute("finalPrice", finalPrice.doubleValue());
                    model.addAttribute("discountPercent", discountPercent.doubleValue());
                    model.addAttribute("hasCoupon", true);
                } else {
                    model.addAttribute("finalPrice", park.getPrice());
                    model.addAttribute("hasCoupon", false);
                }

                model.addAttribute("price", park.getPrice());
                model.addAttribute("name", park.getName());
            }
        } else {
            model.addAttribute("price", "Thanks! Tarangire National Park is closed for a while");
            model.addAttribute("name", "Tarangire National Park");
            model.addAttribute("hasCoupon", false);
        }

        return "test/tarangire";
    }


    @GetMapping("/ruaha")
    public String showRuaha(Model model) {
        Long parkId = 17L;
        AdminEntity park = adminRepository.findById(parkId).orElse(null);

        if (park != null) {
            if (park.getDeleted()) {
                model.addAttribute("price", "Closed");
                model.addAttribute("name", park.getName());
            } else {
                // CHANGE: Now returns List instead of Optional
                List<CouponEntity> activeCoupons = couponRepository
                        .findByParkIdAndIsActiveTrueAndExpiryDateAfter(parkId, LocalDateTime.now());

                if (!activeCoupons.isEmpty()) {
                    // Use the first active coupon (or choose based on business logic)
                    CouponEntity coupon = activeCoupons.get(0);
                    BigDecimal parkPrice = BigDecimal.valueOf(park.getPrice());
                    BigDecimal discountPercent = coupon.getDiscountPercent();
                    BigDecimal discountAmount = parkPrice.multiply(
                            discountPercent.divide(BigDecimal.valueOf(100)));
                    BigDecimal finalPrice = parkPrice.subtract(discountAmount);

                    model.addAttribute("originalPrice", park.getPrice());
                    model.addAttribute("finalPrice", finalPrice.doubleValue());
                    model.addAttribute("discountPercent", discountPercent.doubleValue());
                    model.addAttribute("hasCoupon", true);
                } else {
                    model.addAttribute("finalPrice", park.getPrice());
                    model.addAttribute("hasCoupon", false);
                }

                model.addAttribute("price", park.getPrice());
                model.addAttribute("name", park.getName());
            }
        } else {
            model.addAttribute("price", "Thanks! Ruaha National Park is closed for a while");
            model.addAttribute("name", "Ruaha National Park");
            model.addAttribute("hasCoupon", false);
        }

        return "test/ruaha";
    }

//    @GetMapping("/ruaha")
//    public String showRuaha(Model model) {
//        Long parkId = 17L;
//        AdminEntity park = adminRepository.findById(parkId).orElse(null);
//
//        if (park != null) {
//            if (park.getDeleted()) {
//                model.addAttribute("price", "Closed");
//                model.addAttribute("name", park.getName());
//            } else {
//                Optional<CouponEntity> activeCoupon = couponRepository
//                        .findByParkIdAndIsActiveTrueAndExpiryDateAfter(parkId, LocalDateTime.now());
//
//                if (activeCoupon.isPresent()) {
//                    CouponEntity coupon = activeCoupon.get();
//                    BigDecimal parkPrice = BigDecimal.valueOf(park.getPrice());
//                    BigDecimal discountPercent = coupon.getDiscountPercent();
//                    BigDecimal discountAmount = parkPrice.multiply(
//                            discountPercent.divide(BigDecimal.valueOf(100)));
//                    BigDecimal finalPrice = parkPrice.subtract(discountAmount);
//
//                    model.addAttribute("originalPrice", park.getPrice());
//                    model.addAttribute("finalPrice", finalPrice.doubleValue());
//                    model.addAttribute("discountPercent", discountPercent.doubleValue());
//                    model.addAttribute("hasCoupon", true);
//                } else {
//                    model.addAttribute("finalPrice", park.getPrice());
//                    model.addAttribute("hasCoupon", false);
//                }
//
//                model.addAttribute("price", park.getPrice());
//                model.addAttribute("name", park.getName());
//            }
//        } else {
//            model.addAttribute("price", "Thanks! Ruaha National Park is closed for a while");
//            model.addAttribute("name", "Ruaha National Park");
//            model.addAttribute("hasCoupon", false);
//        }
//
//        return "test/ruaha";
//    }


    @GetMapping("/serengeti")
    public String showSerengeti(Model model) {
        Long parkId = 22L;
        AdminEntity park = adminRepository.findById(parkId).orElse(null);

        if (park != null) {
            if (park.getDeleted()) {
                model.addAttribute("price", "Closed");
                model.addAttribute("name", park.getName());
            } else {
                // CHECK FOR ACTIVE COUPON - NOW RETURNS LIST
                List<CouponEntity> activeCoupons = couponRepository
                        .findByParkIdAndIsActiveTrueAndExpiryDateAfter(parkId, LocalDateTime.now());

                if (!activeCoupons.isEmpty()) {
                    CouponEntity coupon = activeCoupons.get(0);
                    BigDecimal parkPrice = BigDecimal.valueOf(park.getPrice());
                    BigDecimal discountPercent = coupon.getDiscountPercent();
                    BigDecimal discountAmount = parkPrice.multiply(
                            discountPercent.divide(BigDecimal.valueOf(100)));
                    BigDecimal finalPrice = parkPrice.subtract(discountAmount);

                    // ADD COUPON INFO TO MODEL
                    model.addAttribute("originalPrice", park.getPrice());
                    model.addAttribute("finalPrice", finalPrice.doubleValue());
                    model.addAttribute("discountPercent", discountPercent.doubleValue());
                    model.addAttribute("hasCoupon", true);
                } else {
                    model.addAttribute("finalPrice", park.getPrice());
                    model.addAttribute("hasCoupon", false);
                }

                model.addAttribute("price", park.getPrice()); // Keep original for backward compatibility
                model.addAttribute("name", park.getName());
            }
        } else {
            model.addAttribute("price", "Thanks! Serengeti National Park is closed for a while");
            model.addAttribute("name", "Serengeti National Park");
            model.addAttribute("hasCoupon", false);
        }

        return "test/serengeti";
    }
//
//    @GetMapping("/serengeti")
//    public String showSerengeti(Model model) {
//        Long parkId = 22L;
//        AdminEntity park = adminRepository.findById(parkId).orElse(null);
//
//        if (park != null) {
//            if (park.getDeleted()) {
//                model.addAttribute("price", "Closed");
//                model.addAttribute("name", park.getName());
//            } else {
//                // CHECK FOR ACTIVE COUPON
//                Optional<CouponEntity> activeCoupon = couponRepository
//                        .findByParkIdAndIsActiveTrueAndExpiryDateAfter(parkId, LocalDateTime.now());
//
//                if (activeCoupon.isPresent()) {
//                    CouponEntity coupon = activeCoupon.get();
//                    BigDecimal parkPrice = BigDecimal.valueOf(park.getPrice());
//                    BigDecimal discountPercent = coupon.getDiscountPercent();
//                    BigDecimal discountAmount = parkPrice.multiply(
//                            discountPercent.divide(BigDecimal.valueOf(100)));
//                    BigDecimal finalPrice = parkPrice.subtract(discountAmount);
//
//                    // ADD COUPON INFO TO MODEL
//                    model.addAttribute("originalPrice", park.getPrice());
//                    model.addAttribute("finalPrice", finalPrice.doubleValue());
//                    model.addAttribute("discountPercent", discountPercent.doubleValue());
//                    model.addAttribute("hasCoupon", true);
//                } else {
//                    model.addAttribute("finalPrice", park.getPrice());
//                    model.addAttribute("hasCoupon", false);
//                }
//
//                model.addAttribute("price", park.getPrice()); // Keep original for backward compatibility
//                model.addAttribute("name", park.getName());
//            }
//        } else {
//            model.addAttribute("price", "Thanks! Serengeti National Park is closed for a while");
//            model.addAttribute("name", "Serengeti National Park");
//            model.addAttribute("hasCoupon", false);
//        }
//
//        return "test/serengeti";
//    }



    @GetMapping("/m")
    public String manu() {
        return "test2/you";
    }

    @GetMapping("/hii")
    public String showhii() {
        return "test/hii";
    }

    @GetMapping("/hiii")
    public String showhiii() {
        return "test/hiii";
    }

    @GetMapping("/check")
    public String redirectBooking(HttpSession session,
                                  @RequestParam Long id,
                                  @RequestParam String tour) {
        Object loggedIn = session.getAttribute("loggedInCustomer");
        if (loggedIn != null) {
            return "redirect:/quick/form?id=" + id + "&tour=" + tour;
        } else {
            return "redirect:/bookings/me?tour=" + tour + "&tourId=" + id;
        }
    }


    @GetMapping
    public String s() {
        return "test/delete" ;
    }
}