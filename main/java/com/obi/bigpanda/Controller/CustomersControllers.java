package com.obi.bigpanda.Controller;

import com.obi.bigpanda.Entity.AdminEntity;
import com.obi.bigpanda.Entity.CouponEntity;
import com.obi.bigpanda.Entity.CustomersEntity;
import com.obi.bigpanda.Repository.AdminRepository;
import com.obi.bigpanda.Repository.CouponRepository;
import com.obi.bigpanda.Repository.CustomersRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;


import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Controller
@RequestMapping("/reg")
public class CustomersControllers {

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private SessionLocaleResolver localeResolver;


    @Autowired
    private CouponRepository couponRepository ;


    @Autowired
    private AdminRepository adminRepository ;


    @PostMapping("/submit")
    @ResponseBody
    public ResponseEntity<?> submitForm(@ModelAttribute("customer") CustomersEntity customer,
                                        HttpSession session) {
        try {
            customer.setCreatedAt(java.time.LocalDateTime.now());

            // Check if nickname exists
            if (customersRepository.existsByNickname(customer.getNickname())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Collections.singletonMap("error", "Nickname exists. Please login instead."));
            }

            // Check if password exists (for duplicate password prevention)
            if (customersRepository.existsByPasswordHash(customer.getPasswordHash())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Collections.singletonMap("error", "Account with this password exists. Please login."));
            }

            CustomersEntity savedCustomer = customersRepository.save(customer);
            session.setAttribute("loggedInCustomer", savedCustomer);

            return ResponseEntity.ok(Collections.singletonMap("success", true));

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("error", "Nickname exists. Please login instead."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Registration failed. Please try again."));
        }
    }


    // Changed from @PostMapping to @GetMapping to match frontend's GET request
    @GetMapping("/check-nickname")  // Fixed: Now accepts GET requests
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> checkNickname(@RequestParam String nickname) {
        try {
            if (nickname == null || nickname.trim().length() < 2) {
                return ResponseEntity.ok(Collections.singletonMap("exists", true));
            }

            boolean exists = customersRepository.existsByNickname(nickname.trim());
            return ResponseEntity.ok(Collections.singletonMap("exists", exists));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("exists", true));
        }
    }

    // Returns current logged-in user (GET - correct)
    @GetMapping("/current")
    @ResponseBody
    public ResponseEntity<?> currentUser(HttpSession session) {
        CustomersEntity customer = (CustomersEntity) session.getAttribute("loggedInCustomer");
        if (customer != null) {
            return ResponseEntity.ok(Collections.singletonMap("nickname", customer.getNickname()));
        }
        return ResponseEntity.ok(Collections.singletonMap("nickname", null));
    }


    // Serves the registration form (GET - correct)
    @GetMapping
    public String sho(Model model) {
        model.addAttribute("customer", new CustomersEntity()); // Required for Thymeleaf binding
        return "customers/form";
    }


    @GetMapping("/dashboard")
    public String returnMe(HttpSession session, Model model) {
        CustomersEntity customer = (CustomersEntity) session.getAttribute("loggedInCustomer");
        if (customer != null) {
            model.addAttribute("nickname", customer.getNickname()); // ✅ Correct
        } else {
            model.addAttribute("nickname", "Guest"); // Fallback
        }
        return "customers/dashboard";
    }




//    @GetMapping("/ten")
//    public String showTen() {
//        return "usersProfile/registeredUsersProfiles";
//    }




//    @GetMapping("/ten")
//    public String showTen(Model model) {
//        Map<String, String> parkCouponDays = new HashMap<>();
//
//        System.out.println("=== DEBUG /ten endpoint ===");
//
//        // Map parks to their actual IDs from your coupon data
//        String arushaDays = getCouponDays(12L);          // Arusha National (ID:12)
//        String ngorongoroDays = getCouponDays(14L);      // Ngorongoro Crater (ID:14)
//        String serengetiDays = getCouponDays(22L);       // Serengeti National Parks (ID:22)
//        String manyaraDays = getCouponDays(15L);        // No active coupon in your data (placeholder)
//        String tarangireDays = getCouponDays(7L);        // Tarangire National Park (ID:7)
//        String olduvaiDays = getCouponDays(19L);         // Olduvai Gorge (ID:19)
//        String kilimanjaroDays = getCouponDays(20L);     // Mount Kilimanjaro (ID:20)
//        String ruahaDays = getCouponDays(17L);           // Ruaha National Park (ID:17)
//        String kikuletwaDays = getCouponDays(23L);       // Kikuletwa (ID:23) - Note: Coupon is expired
//        String mkomaziDays = getCouponDays(21L);         // Mkomazi ParkS (ID:21)
//
//        // Debug logs for all parks
//        System.out.println("Arusha days: " + arushaDays);
//        System.out.println("Ngorongoro days: " + ngorongoroDays);
//        System.out.println("Serengeti days: " + serengetiDays);
//        System.out.println("Manyara days: " + manyaraDays);
//        System.out.println("Tarangire days: " + tarangireDays);
//        System.out.println("Olduvai Gorge days: " + olduvaiDays);
//        System.out.println("Mount Kilimanjaro days: " + kilimanjaroDays);
//        System.out.println("Ruaha days: " + ruahaDays);
//        System.out.println("Kikuletwa days: " + kikuletwaDays);
//        System.out.println("Mkomazi days: " + mkomaziDays);
//
//        // Add all parks to the map (keys match HTML data-park-name)
//        parkCouponDays.put("Arusha", arushaDays);
//        parkCouponDays.put("Ngorongoro", ngorongoroDays);
//        parkCouponDays.put("Serengeti", serengetiDays);
//        parkCouponDays.put("Manyara", manyaraDays);
//        parkCouponDays.put("Tarangire", tarangireDays);
//        parkCouponDays.put("Olduvai Gorge", olduvaiDays);
//        parkCouponDays.put("Mount Kilimanjaro", kilimanjaroDays);
//        parkCouponDays.put("Ruaha", ruahaDays);
//        parkCouponDays.put("Kikuletwa", kikuletwaDays);
//        parkCouponDays.put("Mkomazi", mkomaziDays);
//
//        System.out.println("Final parkCouponDays map: " + parkCouponDays);
//
//        model.addAttribute("parkCouponDays", parkCouponDays);
//        return "usersProfile/registeredUsersProfiles";
//    }
//
//    private String getCouponDays(Long parkId) {
//        // Handle parks with no coupon data (e.g., Manyara)
//        if (parkId == null) {
//            System.out.println("No park ID provided - no coupon");
//            return "";
//        }
//
//        System.out.println("Fetching coupon days for parkId: " + parkId);
//
//        List<CouponEntity> activeCoupons = couponRepository
//                .findByParkIdAndIsActiveTrueAndExpiryDateAfter(parkId, LocalDateTime.now());
//
//        System.out.println("Found " + activeCoupons.size() + " active coupons");
//
//        if (!activeCoupons.isEmpty()) {
//            CouponEntity coupon = activeCoupons.get(0);
//            String days = coupon.getDaysUntilExpiry();
//            System.out.println("Coupon days: " + days);
//            return days;
//        }
//
//        System.out.println("No active coupons found");
//        return "";  // Empty string hides the badge (matches th:if condition)
//    }


    @GetMapping("/ten")
    public String showTen(Model model) {
        Map<String, String> parkCouponDays = new HashMap<>();

        // Map parks to their actual IDs from your coupon data
        String arushaDays = getCouponDays(12L);       // Arusha National (ID:12)
        String ngorongoroDays = getCouponDays(14L);   // Ngorongoro Crater (ID:14)
        String serengetiDays = getCouponDays(22L);    // Serengeti National Parks (ID:22)
        String manyaraDays = getCouponDays(15L);      // Manyara National Park (ID:15)
        String tarangireDays = getCouponDays(7L);     // Tarangire National Park (ID:7)
        String olduvaiDays = getCouponDays(19L);      // Olduvai Gorge (ID:19)
        String kilimanjaroDays = getCouponDays(20L);  // Mount Kilimanjaro (ID:20)
        String ruahaDays = getCouponDays(17L);        // Ruaha National Park (ID:17)
        String kikuletwaDays = getCouponDays(23L);    // Kikuletwa (ID:23)
        String mkomaziDays = getCouponDays(21L);      // Mkomazi Park (ID:21)

        // Add all parks to the map (keys match HTML data-park-name)
        parkCouponDays.put("Arusha", arushaDays);
        parkCouponDays.put("Ngorongoro", ngorongoroDays);
        parkCouponDays.put("Serengeti", serengetiDays);
        parkCouponDays.put("Manyara", manyaraDays);
        parkCouponDays.put("Tarangire", tarangireDays);
        parkCouponDays.put("Olduvai Gorge", olduvaiDays);
        parkCouponDays.put("Mount Kilimanjaro", kilimanjaroDays);
        parkCouponDays.put("Ruaha", ruahaDays);
        parkCouponDays.put("Kikuletwa", kikuletwaDays);
        parkCouponDays.put("Mkomazi", mkomaziDays);

        model.addAttribute("parkCouponDays", parkCouponDays);
        return "usersProfile/registeredUsersProfiles";
    }

    private String getCouponDays(Long parkId) {
        // Handle parks with no coupon data (e.g., Manyara)
        if (parkId == null) {
            return "";
        }

        List<CouponEntity> activeCoupons = couponRepository
                .findByParkIdAndIsActiveTrueAndExpiryDateAfter(parkId, LocalDateTime.now());

        if (!activeCoupons.isEmpty()) {
            CouponEntity coupon = activeCoupons.get(0);
            return coupon.getDaysUntilExpiry();
        }

        return "";  // Empty string hides the badge (matches th:if condition)
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Remove the logged-in user from the session
        session.removeAttribute("loggedInCustomer");
        // Invalidate the entire session (optional but recommended for security)
        session.invalidate();
        // Redirect to login page after logout
        return "redirect:/login";
    }


    @GetMapping("login")
    public String ShowT() {
        return "customers/login";
    }


    @PostMapping("/login/submit")
    @ResponseBody
    public ResponseEntity<?> loginSubmit(@RequestParam String nickname,
                                         @RequestParam String password,
                                         HttpSession session) {
        try {
            // Add logging for debugging
            System.out.println("Login attempt for nickname: " + nickname);

            // Find customer by nickname
            CustomersEntity customer = customersRepository.findByNickname(nickname);

            if (customer == null) {
                System.out.println("Nickname not found: " + nickname);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Collections.singletonMap("error", "Invalid nickname or password"));
            }

            // Check if password matches
            if (!customer.getPasswordHash().equals(password)) {
                System.out.println("Invalid password for nickname: " + nickname);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Collections.singletonMap("error", "Invalid nickname or password"));
            }

            // Login successful - set session
            session.setAttribute("loggedInCustomer", customer);
            System.out.println("Login successful for nickname: " + nickname);

            return ResponseEntity.ok(Collections.singletonMap("success", true));

        } catch (Exception e) {
            e.printStackTrace(); // Log the full error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Login failed. Please try again."));
        }
    }
}