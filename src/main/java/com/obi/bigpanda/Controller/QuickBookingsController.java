package com.obi.bigpanda.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.obi.bigpanda.Entity.QuickBookingEntity;
import com.obi.bigpanda.Repository.QuickBookingRepository;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/quick")
public class QuickBookingsController {

    @Autowired
    private QuickBookingRepository quickBookingRepository;

    // Show the form
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("quickBooking", new QuickBookingEntity());
        return "pandaone/quickBookings/quickBookingForm"; // your HTML file path under templates
    }


    @PostMapping("/book")
    public String submitForm(@ModelAttribute QuickBookingEntity quickBooking, Model model) {
        quickBooking.setCreatedAt(LocalDateTime.now());
        quickBooking.setCategory("Quick");
        quickBookingRepository.save(quickBooking);

        // Pass the user's name to the success page
        model.addAttribute("userName", quickBooking.getUserName());
        return "pandaone/quickBookings/quickBookingSuccess"; // success page template
    }

    @GetMapping("/list")
    public String listBookings(Model model) {
        model.addAttribute("bookings", quickBookingRepository.findAll());
        return "pandaone/quickBookings/bookingList";
    }
}
