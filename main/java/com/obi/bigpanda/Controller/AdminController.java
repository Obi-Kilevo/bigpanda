
package com.obi.bigpanda.Controller;

import com.obi.bigpanda.Entity.AdminEntity;
import com.obi.bigpanda.Entity.BookingEntity;
import com.obi.bigpanda.Entity.CouponEntity;
import com.obi.bigpanda.Repository.AdminRepository;
import com.obi.bigpanda.Repository.BookingRepository;
import com.obi.bigpanda.Repository.CouponRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminRepository adminRepository;
    private final BookingRepository bookingRepository;
    private final CouponRepository couponRepository; // Add this


    public AdminController(AdminRepository adminRepository, BookingRepository bookingRepository,    CouponRepository couponRepository) {
        this.adminRepository = adminRepository;
        this.bookingRepository = bookingRepository;
        this.couponRepository = couponRepository;
    }


    @GetMapping("/coupons/add")
    public String showAddCouponForm(Model model) {
        List<AdminEntity> activeTours = adminRepository.findAllActiveTours();
        model.addAttribute("coupon", new CouponEntity());
        model.addAttribute("tours", activeTours);
        return "pandaone/admin/add-coupon";
    }

//    @GetMapping("/coupons")
//    public String showAllCoupons(Model model) {
//        List<CouponEntity> coupons = couponRepository.findAll();
//        List<AdminEntity> activeTours = adminRepository.findAllActiveTours();
//
//        Map<Long, String> parkNames = activeTours.stream()
//                .collect(Collectors.toMap(AdminEntity::getId, AdminEntity::getName));
//
//        model.addAttribute("coupons", coupons);
//        model.addAttribute("parkNames", parkNames);
//        model.addAttribute("now", LocalDateTime.now());  // <-- add this
//
//        return "pandaone/admin/coupons";
//    }


//
//    @GetMapping("/coupons")
//    public String showAllCoupons(Model model) {
//        List<CouponEntity> coupons = couponRepository.findAll();
//        List<AdminEntity> activeTours = adminRepository.findAllActiveTours();
//
//        Map<Long, String> parkNames = activeTours.stream()
//                .collect(Collectors.toMap(AdminEntity::getId, AdminEntity::getName));
//
//        // Add days until expiry calculation
//        coupons.forEach(coupon -> {
//            long daysLeft = coupon.getDaysUntilExpiry();
//            coupon.setDaysUntilExpiry(daysLeft);
//        });
//
//        model.addAttribute("coupons", coupons);
//        model.addAttribute("parkNames", parkNames);
//        model.addAttribute("now", LocalDateTime.now());
//
//        return "pandaone/admin/coupons";
//    }



//    @GetMapping("/coupons")
//    public String showAllCoupons(Model model) {
//        List<CouponEntity> coupons = couponRepository.findAll();
//        List<AdminEntity> activeTours = adminRepository.findAllActiveTours();
//
//        Map<Long, String> parkNames = activeTours.stream()
//                .collect(Collectors.toMap(AdminEntity::getId, AdminEntity::getName));
//
//        // No calculation needed - method returns display text
//        model.addAttribute("coupons", coupons);
//        model.addAttribute("parkNames", parkNames);
//        model.addAttribute("now", LocalDateTime.now());
//
//        return "pandaone/admin/coupons";
//    }

    @GetMapping("/coupons")
    public String showAllCoupons(Model model) {
        List<CouponEntity> coupons = couponRepository.findAll();
        List<AdminEntity> activeTours = adminRepository.findAllActiveTours();

        Map<Long, String> parkNames = activeTours.stream()
                .collect(Collectors.toMap(AdminEntity::getId, AdminEntity::getName));

        // Remove these lines - calculation is now done in getDaysUntilExpiry()
        // coupons.forEach(coupon -> {
        //     long daysLeft = coupon.getDaysUntilExpiry();
        //     coupon.setDaysUntilExpiry(daysLeft);
        // });

        model.addAttribute("coupons", coupons);
        model.addAttribute("parkNames", parkNames);
        model.addAttribute("now", LocalDateTime.now());

        return "pandaone/admin/coupons";
    }

    // Create coupon
    @PostMapping("/coupons/add")
    public String addCoupon(@ModelAttribute CouponEntity coupon,
                            @RequestParam Long parkId) {
        coupon.setParkId(parkId);
        coupon.setActive(true);
        coupon.setCreatedAt(LocalDateTime.now());
        couponRepository.save(coupon);
        return "redirect:/admin/tours";
    }


    // Delete coupon endpoint
    @GetMapping("/coupons/delete/{id}")
    public String deleteCoupon(@PathVariable Long id) {
        couponRepository.deleteById(id);
        return "redirect:/admin/coupons";
    }


    // Optional: Deactivate coupon instead of deleting (soft delete)
    @GetMapping("/coupons/deactivate/{id}")
    public String deactivateCoupon(@PathVariable Long id) {
        Optional<CouponEntity> coupon = couponRepository.findById(id);
        if (coupon.isPresent()) {
            CouponEntity couponEntity = coupon.get();
            couponEntity.setActive(false);
            couponRepository.save(couponEntity);
        }
        return "redirect:/admin/coupons";
    }

// ... rest of your existing methods (tours, bookings, etc.) ...

//    @GetMapping("/tours")
//    public String showAllTours(Model model) {
//        List<AdminEntity> tours = adminRepository.findAllActiveTours();
//
//        for (AdminEntity tour : tours) {
//            List<CouponEntity> activeCoupons = couponRepository
//                    .findByParkIdAndIsActiveTrueAndExpiryDateAfter(tour.getId(), LocalDateTime.now());
//
//            if (!activeCoupons.isEmpty()) {
//                CouponEntity coupon = activeCoupons.get(0);
//
//                BigDecimal tourPrice = BigDecimal.valueOf(tour.getPrice());
//                BigDecimal discountPercent = coupon.getDiscountPercent();
//
//                BigDecimal discountAmount = tourPrice.multiply(
//                        discountPercent.divide(BigDecimal.valueOf(100)));
//                BigDecimal finalPrice = tourPrice.subtract(discountAmount);
//
//                tour.setFinalPrice(finalPrice.doubleValue());
//                tour.setHasActiveCoupon(true);
//                tour.setDiscountPercent(discountPercent.doubleValue());
//
//                // ADD THIS LINE to pass the coupon
//                tour.setActiveCoupon(coupon);
//            } else {
//                tour.setFinalPrice(tour.getPrice());
//                tour.setHasActiveCoupon(false);
//            }
//        }
//
//        model.addAttribute("tours", tours);
//        return "pandaone/admin/tours";
//    }



//
//@GetMapping("/tours")
//public String showAllTours(Model model) {
//    List<AdminEntity> tours = adminRepository.findAllActiveTours();
//
//    for (AdminEntity tour : tours) {
//        List<CouponEntity> activeCoupons = couponRepository
//                .findByParkIdAndIsActiveTrueAndExpiryDateAfter(tour.getId(), LocalDateTime.now());
//
//        if (!activeCoupons.isEmpty()) {
//            CouponEntity coupon = activeCoupons.get(0);
//
//            BigDecimal tourPrice = BigDecimal.valueOf(tour.getPrice());
//            BigDecimal discountPercent = coupon.getDiscountPercent();
//
//            BigDecimal discountAmount = tourPrice.multiply(
//                    discountPercent.divide(BigDecimal.valueOf(100)));
//            BigDecimal finalPrice = tourPrice.subtract(discountAmount);
//
//            tour.setFinalPrice(finalPrice.doubleValue());
//            tour.setHasActiveCoupon(true);
//            tour.setDiscountPercent(discountPercent.doubleValue());
//
//            // ADD THIS LINE to pass the coupon
//            tour.setActiveCoupon(coupon);
//        } else {
//            tour.setFinalPrice(tour.getPrice());
//            tour.setHasActiveCoupon(false);
//        }
//    }
//
//    model.addAttribute("tours", tours);
//    return "pandaone/admin/tours";
//}

@GetMapping("/tours")
public String showAllTours(Model model) {
    List<AdminEntity> tours = adminRepository.findAllActiveTours();

    for (AdminEntity tour : tours) {
        // CHANGE: Now returns List instead of Optional
        List<CouponEntity> activeCoupons = couponRepository
                .findByParkIdAndIsActiveTrueAndExpiryDateAfter(tour.getId(), LocalDateTime.now());

        if (!activeCoupons.isEmpty()) {
            // Use the first active coupon (or choose based on business logic)
            CouponEntity coupon = activeCoupons.get(0);

            // Convert Double to BigDecimal for calculations
            BigDecimal tourPrice = BigDecimal.valueOf(tour.getPrice());
            BigDecimal discountPercent = coupon.getDiscountPercent();

            // Calculate discounted price
            BigDecimal discountAmount = tourPrice.multiply(
                    discountPercent.divide(BigDecimal.valueOf(100)));
            BigDecimal finalPrice = tourPrice.subtract(discountAmount);

            // Store as transient data (you'll need to add these to AdminEntity)
            tour.setFinalPrice(finalPrice.doubleValue());
            tour.setHasActiveCoupon(true);
            tour.setDiscountPercent(discountPercent.doubleValue());
        } else {
            tour.setFinalPrice(tour.getPrice());
            tour.setHasActiveCoupon(false);
        }
    }

    model.addAttribute("tours", tours);
    return "pandaone/admin/tours";
}


    // Show deleted tours (for restoration)
    @GetMapping("/tours/deleted")
    public String showDeletedTours(Model model) {
        List<AdminEntity> deletedTours = adminRepository.findAllDeletedTours();
        model.addAttribute("deletedTours", deletedTours);
        return "pandaone/admin/deleted-tours"; // You'll need to create this view
    }

    // Restore tour endpoint
    @GetMapping("/tours/restore/{id}")
    public String restoreTour(@PathVariable Long id) {
        adminRepository.restoreById(id);
        return "redirect:/admin/tours/deleted"; // Redirect back to deleted tours list
    }

    // Show add tour form
    @GetMapping("/tours/add")
    public String showAddTourForm(Model model) {
        model.addAttribute("tour", new AdminEntity());
        return "pandaone/admin/add-tour";
    }



    @PostMapping("/tours/add")
    public String addTour(@ModelAttribute AdminEntity tour) {
        tour.setCreatedAt(LocalDateTime.now());
        tour.setUpdatedAt(LocalDateTime.now());
        adminRepository.save(tour);
        return "redirect:/admin/tours";
    }

    // Show edit tour form
    @GetMapping("/tours/edit/{id}")
    public String showEditTourForm(@PathVariable Long id, Model model) {
        AdminEntity tour = adminRepository.findById(id).orElse(null);
        model.addAttribute("tour", tour);
        return "pandaone/admin/edit-tour";
    }


    @PostMapping("/tours/update/{id}")
    public String updateTour(@PathVariable Long id, @ModelAttribute AdminEntity updatedTour) {
        AdminEntity existingTour = adminRepository.findById(id).orElse(null);

        // Preserve original createdAt, update timestamp
        updatedTour.setCreatedAt(existingTour.getCreatedAt());
        updatedTour.setUpdatedAt(LocalDateTime.now());

        adminRepository.save(updatedTour);
        return "redirect:/admin/tours";
    }


    @GetMapping("/tours/delete/{id}")
    public String deleteTour(@PathVariable Long id) {
        adminRepository.softDeleteById(id);
        return "redirect:/admin/tours";
    }


    // View all bookings
    @GetMapping("/bookings")
    public String viewAllBookings(Model model) {
        List<BookingEntity> bookings = bookingRepository.findAll();
        model.addAttribute("bookings", bookings);
        return "pandaone/admin/bookings";
    }

    // Add these methods to your AdminController:

    // Show edit coupon form
    @GetMapping("/coupons/edit/{id}")
    public String showEditCouponForm(@PathVariable Long id, Model model) {
        CouponEntity coupon = couponRepository.findById(id).orElse(null);
        model.addAttribute("coupon", coupon);
        return "pandaone/admin/edit-coupon"; // Your edit template
    }

    // Update coupon
    @PostMapping("/coupons/update")
    public String updateCoupon(@ModelAttribute CouponEntity coupon) {
        couponRepository.save(coupon);
        return "redirect:/admin/coupons";
    }

}