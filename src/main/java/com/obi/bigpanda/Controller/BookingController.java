//package com.obi.bigpanda.Controller;
//
//import com.obi.bigpanda.Entity.AdminEntity;
//import com.obi.bigpanda.Entity.BookingEntity;
//import com.obi.bigpanda.Repository.AdminRepository;
//import com.obi.bigpanda.Repository.BookingRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/bookings")
//public class BookingController {
//
//    @Autowired
//    private BookingRepository bookingRepository;
//
//    @Autowired
//    private AdminRepository adminRepository;
//
//    // Show booking form for a specific park/tour
//    @GetMapping("/new/{tourId}")
//    public String showBookingForm(@PathVariable Long tourId, Model model) {
//        Optional<AdminEntity> tour = adminRepository.findById(tourId);
//        if (tour.isPresent()) {
//            BookingEntity booking = new BookingEntity();
//            booking.setTour(tour.get());
//            model.addAttribute("tour", tour.get());
//            model.addAttribute("booking", booking);
//            return "pandaone/bookings/new-booking";
//        }
//        return "redirect:/admin/dashboard";
//    }
//
//    // Handle form submission
//    @PostMapping("/save")
//    public String saveBooking(@ModelAttribute BookingEntity booking) {
//        bookingRepository.save(booking);
//        return "redirect:/bookings/list";
//    }
//
//    // Show all bookings for admin
//    @GetMapping("/list")
//    public String listBookings(Model model) {
//        model.addAttribute("bookings", bookingRepository.findAll());
//        return "pandaone/bookings/list-bookings";
//    }
//}


package com.obi.bigpanda.Controller;


import com.obi.bigpanda.Entity.AdminEntity;
import com.obi.bigpanda.Entity.BookingEntity;
import com.obi.bigpanda.Repository.AdminRepository;
import com.obi.bigpanda.Repository.BookingRepository;
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



    @GetMapping ("/me")
    public String showMeForm(@RequestParam(required = false) Long tourId, Model model) {
        BookingEntity booking = new BookingEntity();
        if (tourId != null) {
            booking.setTourId(tourId);

            // Automatically get the tour name from the database
            AdminEntity tour = adminRepository.findById(tourId).orElse(null);
            if (tour != null) {
                booking.setTourPackage(tour.getName()); // Set the tour name automatically
            }
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