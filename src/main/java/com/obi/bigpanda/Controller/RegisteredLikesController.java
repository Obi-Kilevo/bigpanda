package com.obi.bigpanda.Controller;




import com.obi.bigpanda.Entity.CustomersEntity;
import com.obi.bigpanda.Entity.ParkLikesEntity;
import com.obi.bigpanda.Repository.CustomersRepository;
import com.obi.bigpanda.Repository.ParkLikesRepository;
import com.obi.bigpanda.Repository.ParksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/reg/likes")
public class RegisteredLikesController {

    @Autowired
    private ParkLikesRepository parkLikesRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private ParksRepository parksRepository;

    // Handle like/dislike toggle for registered customers
    @PostMapping("/park/{parkId}")
    @ResponseBody
    public String handleParkLike(@PathVariable Long parkId,
                                 @RequestParam Long customerId) {

        // Check if customer already liked this park
        ParkLikesEntity existingLike = parkLikesRepository
                .findByCustomerIdAndParkId(customerId, parkId);

        if (existingLike != null) {
            // Toggle like/dislike
            existingLike.setLiked(!existingLike.getLiked());
            existingLike.setActive(true); // Keep record active
            parkLikesRepository.save(existingLike);
        } else {
            // Create new like
            ParkLikesEntity newLike = new ParkLikesEntity();
            newLike.setParkId(parkId);
            newLike.setCustomerId(customerId);
            newLike.setGuestSessionId(null); // Explicitly null for registered users
            newLike.setLiked(true);
            newLike.setActive(true);
            newLike.setCreatedAt(LocalDateTime.now());
            parkLikesRepository.save(newLike);
        }

        return "success";
    }


    // Statistics for registered customers only
    @GetMapping("/stats")
    public String showRegisteredStats(Model model) {

        // Get total likes from registered customers only
        Long totalLikes = parkLikesRepository.countByCustomerIdNotNullAndLikedTrue();
        Long totalDislikes = parkLikesRepository.countByCustomerIdNotNullAndLikedFalse();

        // FIX: Use Integer keys instead of Long to match Thymeleaf sequence
        Map<Integer, Long> parkLikeCounts = new HashMap<>();
        Map<Integer, Long> parkDislikeCounts = new HashMap<>();

        for (int i = 1; i <= 10; i++) {
            Long parkId = (long) i; // Convert to Long for repository calls

            // Get counts from repository
            Long likes = parkLikesRepository.countByParkIdAndCustomerIdNotNullAndLikedTrue(parkId);
            Long dislikes = parkLikesRepository.countByParkIdAndCustomerIdNotNullAndLikedFalse(parkId);

            // FIX: Use Integer keys and handle null values
            parkLikeCounts.put(i, likes != null ? likes : 0L);
            parkDislikeCounts.put(i, dislikes != null ? dislikes : 0L);
        }

        // Calculate averages with safety check
        double averageLikes = totalLikes != null ? totalLikes / 10.0 : 0.0;
        double averageDislikes = totalDislikes != null ? totalDislikes / 10.0 : 0.0;

        // Add to model
        model.addAttribute("totalLikes", totalLikes != null ? totalLikes : 0);
        model.addAttribute("totalDislikes", totalDislikes != null ? totalDislikes : 0);
        model.addAttribute("averageLikes", averageLikes);
        model.addAttribute("averageDislikes", averageDislikes);
        model.addAttribute("parkLikeCounts", parkLikeCounts);
        model.addAttribute("parkDislikeCounts", parkDislikeCounts);

        return "customers/registered/stats";
    }
}


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
