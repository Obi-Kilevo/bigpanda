//
//package com.obi.bigpanda.Controller;
//
//
//import com.obi.bigpanda.Entity.CustomersEntity;
//import com.obi.bigpanda.Entity.ParkLikesEntity;
//import com.obi.bigpanda.Repository.CustomersRepository;
//import com.obi.bigpanda.Repository.ParkLikesRepository;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Controller
//@RequestMapping("/likes")
//public class ParkLikesController {
//
//    @Autowired
//    private ParkLikesRepository parkLikesRepository;
//    @Autowired
//    private CustomersRepository customersRepository;
//
//
//    @PostMapping("/park/{parkId}")
//    @ResponseBody
//    public String toggleParkLike(@PathVariable Long parkId,
//                                 @RequestBody Map<String, Boolean> payload,
//                                 HttpSession session) {
//
//        String guestSessionId = session.getId();
//        Boolean liked = payload.get("liked");
//
//        // Find existing like for this user+park
//        ParkLikesEntity existingLike = parkLikesRepository
//                .findByParkIdAndGuestSessionId(parkId, guestSessionId);
//
//        if (existingLike != null) {
//            // Update existing like
//            existingLike.setLiked(liked);
//            parkLikesRepository.save(existingLike);
//        } else {
//            // Create new like
//            ParkLikesEntity newLike = new ParkLikesEntity();
//            newLike.setParkId(parkId);
//            newLike.setGuestSessionId(guestSessionId);
//            newLike.setLiked(liked);
//            parkLikesRepository.save(newLike);
//        }
//
//        return liked ? "Liked park: " + parkId : "Disliked park: " + parkId;
//    }
//
//
//    @GetMapping("/admin/stats")
//    public String getParkStats(Model model) {
//        Map<Integer, Long> parkLikeCounts = new HashMap<>();
//        Map<Integer, Long> parkDislikeCounts = new HashMap<>();
//        long totalLikes = 0;
//        long totalDislikes = 0; // New variable for total dislikes
//
//        for (int i = 1; i <= 10; i++) {
//            Long parkId = (long) i;
//
//            // Count likes (existing)
//            long likeCount = parkLikesRepository.countByParkIdAndCustomerIdIsNullAndLikedTrue(parkId);
//            parkLikeCounts.put(i, likeCount);
//            totalLikes += likeCount;
//
//            // Count dislikes (existing)
//            long dislikeCount = parkLikesRepository.countByParkIdAndCustomerIdIsNullAndLikedFalse(parkId);
//            parkDislikeCounts.put(i, dislikeCount);
//            totalDislikes += dislikeCount; // Add to total dislikes
//        }
//
//        double averageLikes = totalLikes / 10.0;
//        double averageDislikes = totalDislikes / 10.0; // Calculate average dislikes
//
//        model.addAttribute("parkLikeCounts", parkLikeCounts);
//        model.addAttribute("parkDislikeCounts", parkDislikeCounts);
//        model.addAttribute("totalLikes", totalLikes);
//        model.addAttribute("totalDislikes", totalDislikes); // Add to model
//        model.addAttribute("averageLikes", averageLikes);
//        model.addAttribute("averageDislikes", averageDislikes); // Add to model
//
//        return "office/park-stats";
//    }
//
//
//    @GetMapping("/admin")
//    public String getAdminView(Model model) {
//        List<ParkLikesEntity> allLikes = parkLikesRepository.findAll();
//        model.addAttribute("parkLikes", allLikes);
//        return "office/admin-likes";
//
//    }
//}


package com.obi.bigpanda.Controller;

import com.obi.bigpanda.Entity.ParkLikesEntity;
import com.obi.bigpanda.Repository.CustomersRepository;
import com.obi.bigpanda.Repository.ParkLikesRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/likes")
public class ParkLikesController {

    @Autowired
    private ParkLikesRepository parkLikesRepository;
    @Autowired
    private CustomersRepository customersRepository;

    // Fixed: Uses YOUR existing repository methods
    @PostMapping("/park/{parkId}")
    @ResponseBody
    public String toggleParkLike(
            @PathVariable Long parkId,
            @RequestParam(required = false) Boolean liked, // For guests
            @RequestParam(required = false) Long customerId, // For registered users
            HttpSession session) {

        String guestSessionId = session.getId();
        if (liked == null) liked = true; // Default to "like" if not specified

        ParkLikesEntity existingLike;

        // Case 1: Registered user (uses YOUR findByCustomerIdAndParkId)
        if (customerId != null) {
            existingLike = parkLikesRepository.findByCustomerIdAndParkId(customerId, parkId);
        }
        // Case 2: Unregistered user (uses YOUR findByParkIdAndGuestSessionId)
        else {
            existingLike = parkLikesRepository.findByParkIdAndGuestSessionId(parkId, guestSessionId);
        }

        if (existingLike != null) {
            // Update existing like
            existingLike.setLiked(liked);
            parkLikesRepository.save(existingLike);
        } else {
            // Create new like
            ParkLikesEntity newLike = new ParkLikesEntity();
            newLike.setParkId(parkId);
            newLike.setLiked(liked);
            newLike.setCustomerId(customerId); // null for guests
            if (customerId == null) {
                newLike.setGuestSessionId(guestSessionId); // Only for guests
            }
            parkLikesRepository.save(newLike);
        }

        return liked ? "Liked park: " + parkId : "Disliked park: " + parkId;
    }


//     Admin stats (unchanged, uses your existing count methods)
    @GetMapping("/admin/stats")
    public String getParkStats(Model model) {
        Map<Integer, Long> parkLikeCounts = new HashMap<>();
        Map<Integer, Long> parkDislikeCounts = new HashMap<>();
        long totalLikes = 0;
        long totalDislikes = 0;

        for (int i = 1; i <= 10; i++) {
            Long parkId = (long) i;

            // Unregistered likes (uses YOUR countByParkIdAndCustomerIdIsNullAndLikedTrue)
            long likeCount = parkLikesRepository.countByParkIdAndCustomerIdIsNullAndLikedTrue(parkId);
            parkLikeCounts.put(i, likeCount);
            totalLikes += likeCount;

            // Unregistered dislikes (uses YOUR countByParkIdAndCustomerIdIsNullAndLikedFalse)
            long dislikeCount = parkLikesRepository.countByParkIdAndCustomerIdIsNullAndLikedFalse(parkId);
            parkDislikeCounts.put(i, dislikeCount);
            totalDislikes += dislikeCount;
        }

        double averageLikes = totalLikes / 10.0;
        double averageDislikes = totalDislikes / 10.0;

        model.addAttribute("parkLikeCounts", parkLikeCounts);
        model.addAttribute("parkDislikeCounts", parkDislikeCounts);
        model.addAttribute("totalLikes", totalLikes);
        model.addAttribute("totalDislikes", totalDislikes);
        model.addAttribute("averageLikes", averageLikes);
        model.addAttribute("averageDislikes", averageDislikes);

        return "office/park-stats";
    }



    @GetMapping("/admin")
    public String getAdminView(Model model) {
        List<ParkLikesEntity> allLikes = parkLikesRepository.findAll();
        model.addAttribute("parkLikes", allLikes);
        return "office/admin-likes";
    }
}