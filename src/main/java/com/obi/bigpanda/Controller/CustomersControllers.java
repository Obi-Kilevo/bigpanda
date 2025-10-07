package com.obi.bigpanda.Controller;

import com.obi.bigpanda.Entity.CustomersEntity;
import com.obi.bigpanda.Repository.CustomersRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reg")
public class CustomersControllers {

    @Autowired
    private CustomersRepository customersRepository;

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("customer", new CustomersEntity());
        return "customers/form"; // templates/customers/form.html
    }

//    @PostMapping("/submit")
//    public String submitForm(@ModelAttribute("customer") CustomersEntity customer, Model model) {
//        customer.setCreatedAt(java.time.LocalDateTime.now());
//        customersRepository.save(customer);
//        model.addAttribute("customer", customer);
//        return "customers/success";
@PostMapping("/submit")
public String submitForm(@ModelAttribute("customer") CustomersEntity customer,
                         HttpSession session, // Inject HttpSession
                         Model model) {
    customer.setCreatedAt(java.time.LocalDateTime.now());

    try {
        customersRepository.save(customer);
        // Store the entire customer object in session after successful registration
        session.setAttribute("loggedInCustomer", customer);

        model.addAttribute("customer", customer);
        return "customers/success";

    } catch (DataIntegrityViolationException e) {
        // Handle case where nickname might already exist
        model.addAttribute("errorMessage", "Nickname already exists. Please choose a different one.");
        return "customers/form";
    }
}

//    @PostMapping("/submit")
//    public String submitForm(@ModelAttribute("customer") CustomersEntity customer, Model model, HttpSession session) {
//        customer.setCreatedAt(java.time.LocalDateTime.now());
//        CustomersEntity savedCustomer = customersRepository.save(customer);
//
//        // Set the logged-in customer ID in the session
//        session.setAttribute("loggedInCustomerId", savedCustomer.getId());
//
//        model.addAttribute("customer", savedCustomer);
//        return "customers/success";
//    }



//    @PostMapping("/submit")
//    public String submitForm(@ModelAttribute("customer") CustomersEntity customer, Model model) {
//        try {
//            customer.setCreatedAt(java.time.LocalDateTime.now());
//            customersRepository.save(customer);
//            model.addAttribute("customer", customer);
//            return "customers/success";
//        } catch (DataIntegrityViolationException e) {
//            model.addAttribute("error", "Nickname already exists. Please choose another.");
//            return "customers/form"; // Return back to form with error
//        }
//    }
}

//
//package com.obi.bigpanda.Controller;
//
//import com.obi.bigpanda.Entity.CustomersEntity;
//import com.obi.bigpanda.Entity.ParkLikesEntity;
//import com.obi.bigpanda.Repository.CustomersRepository;
//import com.obi.bigpanda.Repository.ParkLikesRepository;
//import com.obi.bigpanda.Repository.ParksRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
//@Controller
//@RequestMapping("/reg/likes")
//public class RegisteredLikesController {
//
//    @Autowired
//    private ParkLikesRepository parkLikesRepository;
//
//    @Autowired
//    private CustomersRepository customersRepository;
//
//    @Autowired
//    private ParksRepository parksRepository;
//
//    // Handle like/dislike toggle for registered customers
//    @PostMapping("/park/{parkId}")
//    @ResponseBody
//    public String handleParkLike(@PathVariable Long parkId,
//                                 @RequestParam Long customerId) {
//
//        // Check if customer already liked this park
//        ParkLikesEntity existingLike = parkLikesRepository
//                .findByCustomerIdAndParkId(customerId, parkId);
//
//        if (existingLike != null) {
//            // Toggle like/dislike
//            existingLike.setLiked(!existingLike.getLiked());
//            existingLike.setActive(true); // Keep record active
//            parkLikesRepository.save(existingLike);
//        } else {
//            // Create new like
//            ParkLikesEntity newLike = new ParkLikesEntity();
//            newLike.setParkId(parkId);
//            newLike.setCustomerId(customerId);
//            newLike.setGuestSessionId(null); // Explicitly null for registered users
//            newLike.setLiked(true);
//            newLike.setActive(true);
//            newLike.setCreatedAt(LocalDateTime.now());
//            parkLikesRepository.save(newLike);
//        }
//
//        return "success";
//    }
//
//    // Statistics for registered customers only
//    @GetMapping("/stats")
//    public String showRegisteredStats(Model model) {
//
//        // Get total likes from registered customers only
//        Long totalLikes = parkLikesRepository.countByCustomerIdNotNullAndLikedTrue();
//        Long totalDislikes = parkLikesRepository.countByCustomerIdNotNullAndLikedFalse();
//
//        // Get likes per park for registered customers
//        Map<Long, Long> parkLikeCounts = new HashMap<>();
//        Map<Long, Long> parkDislikeCounts = new HashMap<>();
//
//        for (long i = 1; i <= 10; i++) {
//            Long likes = parkLikesRepository.countByParkIdAndCustomerIdNotNullAndLikedTrue(i);
//            Long dislikes = parkLikesRepository.countByParkIdAndCustomerIdNotNullAndLikedFalse(i);
//            parkLikeCounts.put(i, likes != null ? likes : 0L);
//            parkDislikeCounts.put(i, dislikes != null ? dislikes : 0L);
//        }
//
//        // Calculate averages
//        double averageLikes = totalLikes / 10.0;
//        double averageDislikes = totalDislikes / 10.0;
//
//        // Add to model
//        model.addAttribute("totalLikes", totalLikes);
//        model.addAttribute("totalDislikes", totalDislikes);
//        model.addAttribute("averageLikes", averageLikes);
//        model.addAttribute("averageDislikes", averageDislikes);
//        model.addAttribute("parkLikeCounts", parkLikeCounts);
//        model.addAttribute("parkDislikeCounts", parkDislikeCounts);
//
//        return "customers/registered/stats"; // templates/registered/stats.html
//    }
//}