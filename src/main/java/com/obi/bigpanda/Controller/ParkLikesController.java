
package com.obi.bigpanda.Controller;


import com.obi.bigpanda.Entity.CustomersEntity;
import com.obi.bigpanda.Entity.ParkLikesEntity;
import com.obi.bigpanda.Repository.CustomersRepository;
import com.obi.bigpanda.Repository.ParkLikesRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/likes")
public class ParkLikesController {

    @Autowired
    private ParkLikesRepository parkLikesRepository;
    @Autowired
    private CustomersRepository customersRepository;


    @PostMapping("/park/{parkId}")
    @ResponseBody
    public String toggleParkLike(@PathVariable Long parkId,
                                 @RequestBody Map<String, Boolean> payload,
                                 HttpSession session) {

        String guestSessionId = session.getId();
        Boolean liked = payload.get("liked");

        // Find existing like for this user+park
        ParkLikesEntity existingLike = parkLikesRepository
                .findByParkIdAndGuestSessionId(parkId, guestSessionId);

        if (existingLike != null) {
            // Update existing like
            existingLike.setLiked(liked);
            parkLikesRepository.save(existingLike);
        } else {
            // Create new like
            ParkLikesEntity newLike = new ParkLikesEntity();
            newLike.setParkId(parkId);
            newLike.setGuestSessionId(guestSessionId);
            newLike.setLiked(liked);
            parkLikesRepository.save(newLike);
        }

        return liked ? "Liked park: " + parkId : "Disliked park: " + parkId;
    }


    @GetMapping("/admin/stats")
    public String getParkStats(Model model) {
        Map<Integer, Long> parkLikeCounts = new HashMap<>();
        Map<Integer, Long> parkDislikeCounts = new HashMap<>();
        long totalLikes = 0;
        long totalDislikes = 0; // New variable for total dislikes

        for (int i = 1; i <= 10; i++) {
            Long parkId = (long) i;

            // Count likes (existing)
            long likeCount = parkLikesRepository.countByParkIdAndCustomerIdIsNullAndLikedTrue(parkId);
            parkLikeCounts.put(i, likeCount);
            totalLikes += likeCount;

            // Count dislikes (existing)
            long dislikeCount = parkLikesRepository.countByParkIdAndCustomerIdIsNullAndLikedFalse(parkId);
            parkDislikeCounts.put(i, dislikeCount);
            totalDislikes += dislikeCount; // Add to total dislikes
        }

        double averageLikes = totalLikes / 10.0;
        double averageDislikes = totalDislikes / 10.0; // Calculate average dislikes

        model.addAttribute("parkLikeCounts", parkLikeCounts);
        model.addAttribute("parkDislikeCounts", parkDislikeCounts);
        model.addAttribute("totalLikes", totalLikes);
        model.addAttribute("totalDislikes", totalDislikes); // Add to model
        model.addAttribute("averageLikes", averageLikes);
        model.addAttribute("averageDislikes", averageDislikes); // Add to model

        return "office/park-stats";
    }


    @GetMapping("/admin")
    public String getAdminView(Model model) {
        List<ParkLikesEntity> allLikes = parkLikesRepository.findAll();
        model.addAttribute("parkLikes", allLikes);
        return "office/admin-likes";

    }


//    // Personal like history for registered customers
//    @GetMapping("/my-history")
//    public String showMyLikeHistory(@RequestParam Long customerId,
//                                    Model model,
//                                    HttpSession session) {
//
//        // Optional: Add authentication check here
//        // Long loggedInCustomerId = (Long) session.getAttribute("customerId");
//        // if (!customerId.equals(loggedInCustomerId)) {
//        //     return "redirect:/login"; // Or error page
//        // }
//
//        // Get customer details
//        CustomersEntity customer = customersRepository.findById(customerId).orElse(null);
//        if (customer == null) {
//            model.addAttribute("error", "Customer not found");
//            return "customers/error";
//        }
//
//        // Get customer's like history
//        List<ParkLikesEntity> customerLikes = parkLikesRepository.findByCustomerIdAndActiveTrue(customerId);
//
//        // Separate liked and disliked parks
//        List<ParkLikesEntity> likedParks = customerLikes.stream()
//                .filter(ParkLikesEntity::getLiked)
//                .collect(Collectors.toList());
//
//        List<ParkLikesEntity> dislikedParks = customerLikes.stream()
//                .filter(like -> !like.getLiked())
//                .collect(Collectors.toList());
//
//        // Add to model
//        model.addAttribute("customer", customer);
//        model.addAttribute("likedParks", likedParks);
//        model.addAttribute("dislikedParks", dislikedParks);
//        model.addAttribute("totalLiked", likedParks.size());
//        model.addAttribute("totalDisliked", dislikedParks.size());
//
//        return "customers/registered/personal-history";
//    }
}








// this was the orriginal one, 1 first
//
//    @GetMapping("/admin/stats")
//    public String getParkStats(Model model) {
//        Map<Integer, Long> parkLikeCounts = new HashMap<>();
//        long totalLikes = 0;
//
//        for (int i = 1; i <= 10; i++) {
//            long count = parkLikesRepository.countByParkIdAndCustomerIdIsNull((long) i);
//            parkLikeCounts.put(i, count);
//            totalLikes += count;
//        }
//
//        double averageLikes = totalLikes / 10.0;
//
//        model.addAttribute("parkLikeCounts", parkLikeCounts);
//        model.addAttribute("totalLikes", totalLikes);
//        model.addAttribute("averageLikes", averageLikes);
//
//        return "office/park-stats";
//    }






//    @GetMapping("/my-likes")
//    @ResponseBody
//    public List<Long> getUserLikedParks(HttpSession session) {
//        String guestSessionId = session.getId();
//
//        // Get all parks this user has liked
//        List<ParkLikesEntity> userLikes = parkLikesRepository
//                .findByGuestSessionIdAndLikedTrue(guestSessionId);
//
//        return userLikes.stream()
//                .map(ParkLikesEntity::getParkId)
//                .collect(Collectors.toList());
//    }


// this is third part , 3rd

//    @GetMapping("/admin/stats")
//    public String getParkStats(Model model) {
//        Map<Integer, Long> parkLikeCounts = new HashMap<>();
//        Map<Integer, Long> parkDislikeCounts = new HashMap<>();
//        long totalLikes = 0;
//
//        for (int i = 1; i <= 10; i++) {
//            Long parkId = (long) i;
//
//            // Count likes
//            long likeCount = parkLikesRepository.countByParkIdAndCustomerIdIsNullAndLikedTrue(parkId);
//            parkLikeCounts.put(i, likeCount);
//            totalLikes += likeCount;
//
//            // Count dislikes
//            long dislikeCount = parkLikesRepository.countByParkIdAndCustomerIdIsNullAndLikedFalse(parkId);
//            parkDislikeCounts.put(i, dislikeCount);
//        }
//
//        double averageLikes = totalLikes / 10.0;
//
//        model.addAttribute("parkLikeCounts", parkLikeCounts);
//        model.addAttribute("parkDislikeCounts", parkDislikeCounts);
//        model.addAttribute("totalLikes", totalLikes);
//        model.addAttribute("averageLikes", averageLikes);
//
//        return "office/park-stats";
//    }
//


// this is second part , 2nd

//    @GetMapping("/admin/stats")
//    public String getParkStats(Model model) {
//        Map<Integer, Long> parkLikeCounts = new HashMap<>();
//        long totalLikes = 0;
//
//        for (int i = 1; i <= 10; i++) {
//            // Count only LIKED parks (liked = true)
//            long count = parkLikesRepository.countByParkIdAndCustomerIdIsNullAndLikedTrue((long) i);
//            parkLikeCounts.put(i, count);
//            totalLikes += count;
//        }
//
//        double averageLikes = totalLikes / 10.0;
//
//        model.addAttribute("parkLikeCounts", parkLikeCounts);
//        model.addAttribute("totalLikes", totalLikes);
//        model.addAttribute("averageLikes", averageLikes);
//
//        return "office/park-stats";
//    }



