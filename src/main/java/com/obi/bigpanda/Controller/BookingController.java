
package com.obi.bigpanda.Controller;


import com.obi.bigpanda.Entity.AdminEntity;
import com.obi.bigpanda.Entity.BookingEntity;
import com.obi.bigpanda.Repository.AdminRepository;
import com.obi.bigpanda.Repository.BookingRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingRepository bookingRepository;
    private final AdminRepository adminRepository;

    public BookingController(BookingRepository bookingRepository, AdminRepository adminRepository) {
        this.bookingRepository = bookingRepository;
        this.adminRepository = adminRepository;
    }



    @GetMapping
    public String getAllBookings(Model model) {
        List<BookingEntity> bookings = bookingRepository.findAll();
        model.addAttribute("bookings", bookings);
        return "pandaone/bookings/bookings";
    }

    @GetMapping("/form")
    public String showBookingForm(@RequestParam(required = false) Long tourId, Model model) {
        BookingEntity booking = new BookingEntity();
        if (tourId != null) {
            booking.setTourId(tourId);
        }
        model.addAttribute("booking", booking);
        model.addAttribute("tours", adminRepository.findAllActiveTours()); // ← FIXED
        return "pandaone/bookings/booking-form";
    }




    @GetMapping("/me")
    public String showMeForm(@RequestParam(required = false) Long tourId,
                             @RequestParam(required = false) String tour,
                             Model model) {
        BookingEntity booking = new BookingEntity();

        if (tourId != null) {
            booking.setTourId(tourId);
            booking.setTourPackage(tour); // Set tour name directly from query param
        }

        model.addAttribute("booking", booking);
        return "pandaone/bookings/me";
    }


    @PostMapping("/submit")
    public String submitBooking(@ModelAttribute BookingEntity booking) {
        booking.setCreatedAt(LocalDateTime.now());
        bookingRepository.save(booking);

        // Redirect with the user's name and tour package as parameters
        return "redirect:/bookings/confirmation?userName=" + booking.getUserName() +
                "&tourPackage=" + booking.getTourPackage();
    }


    @GetMapping("/confirmation")
    public String showConfirmation(@RequestParam String userName, @RequestParam String tourPackage, Model model) {
        model.addAttribute("userName", userName);
        model.addAttribute("tourPackage", tourPackage);
        return "pandaone/bookings/booking-confirmation";
    }



    @GetMapping("/{id}")
    public String getBookingDetails(@PathVariable Long id, Model model) {
        BookingEntity booking = bookingRepository.findById(id).orElse(null);
        model.addAttribute("booking", booking);
        return "pandaone/bookings/booking-details";
    }
}
