package com.obi.bigpanda.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.obi.bigpanda.Entity.QuickBookingEntity;
import com.obi.bigpanda.Entity.CustomersEntity;
import com.obi.bigpanda.Repository.QuickBookingRepository;
import com.obi.bigpanda.Repository.CustomersRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



@Controller
@RequestMapping("/quick")
public class QuickBookingsController {

    @Autowired
    private QuickBookingRepository quickBookingRepository;



    @GetMapping("/form")
    public String showForm(HttpSession session, Model model) {

        // Get the logged-in customer from the session
        CustomersEntity loggedInCustomer = (CustomersEntity) session.getAttribute("loggedInCustomer");
        QuickBookingEntity quickBooking = new QuickBookingEntity();

        // If a customer is logged in, automatically link them to the new booking
        if (loggedInCustomer != null) {
            quickBooking.setCustomer(loggedInCustomer);
        }

        model.addAttribute("quickBooking", quickBooking);

        // Add the loggedInCustomer to the model to display a greeting in the HTML
        model.addAttribute("loggedInCustomer", loggedInCustomer);

        return "pandaone/quickBookings/quickBookingForm";
    }



//    @GetMapping("/list")
//    public String listBookings(Model model) {
//        List<QuickBookingEntity> allBookings = quickBookingRepository.findAllWithCustomer();
//        System.out.println("Number of bookings found: " + allBookings.size()); // Check the console output
//        model.addAttribute("bookings", allBookings);
//        return "pandaone/quickBookings/bookingList";
//    }



//    @PostMapping("/book")
//    public String submitForm(@ModelAttribute QuickBookingEntity quickBooking, Model model) {
//        quickBooking.setCreatedAt(LocalDateTime.now());
//        quickBooking.setCategory("Quick");
//
//        // Customer will be set from session/auth later
//        // quickBooking.setCustomer(loggedInCustomer);
//
//        quickBookingRepository.save(quickBooking);
//        return "pandaone/quickBookings/quickBookingSuccess";
//    }

    @PostMapping("/book")
    public String submitForm(@ModelAttribute QuickBookingEntity quickBooking, HttpSession session) {
        CustomersEntity loggedInCustomer = (CustomersEntity) session.getAttribute("loggedInCustomer");
        if (loggedInCustomer != null) {
            quickBooking.setCustomer(loggedInCustomer);
        }

        quickBooking.setCreatedAt(LocalDateTime.now());
        quickBooking.setCategory("Quick");
        quickBookingRepository.save(quickBooking);
        return "pandaone/quickBookings/quickBookingSuccess";
    }


//    @GetMapping("/list")
//    public String listBookings(Model model) {
//        List<QuickBookingEntity> allBookings = quickBookingRepository.findAllWithCustomer();
//        // Add this log to see how many records are found
//        System.out.println("Number of bookings found: " + allBookings.size());
//        model.addAttribute("bookings", allBookings);
//        return "pandaone/quickBookings/bookingList";
//    }
@GetMapping("/list")
public String listBookings(Model model) {
    List<QuickBookingEntity> allBookings = quickBookingRepository.findAllWithCustomer();

    for (QuickBookingEntity b : allBookings) {
        if (b.getCustomer() != null) {
            System.out.println("Booking ID: " + b.getId() + ", Customer nickname: " + b.getCustomer().getNickname());
        } else {
            System.out.println("Booking ID: " + b.getId() + ", Customer: null (Guest)");
        }
    }

    model.addAttribute("bookings", allBookings);
    return "pandaone/quickBookings/bookingList";
}

//    @GetMapping("/list")
//    public String listBookings(Model model) {
//        List<QuickBookingEntity> allBookings = quickBookingRepository.findAllWithCustomer();
//        // Add this log to see how many records are found
//        System.out.println("Number of bookings found: " + allBookings.size());
//        model.addAttribute("bookings", allBookings);
//        return "pandaone/quickBookings/bookingList";
//    }
    // List bookings
//    @GetMapping("/list")
//    public String listBookings(Model model) {
//        model.addAttribute("bookings", quickBookingRepository.findAll());
//        return "pandaone/quickBookings/bookingList";
//    }




//    @GetMapping("/list")
//    public String listBookings(Model model) {
//        model.addAttribute("bookings", quickBookingRepository.findAllWithCustomer());
//        return "pandaone/quickBookings/bookingList";
//    }
}



//    @PostMapping("/book")
//    public String submitForm(@ModelAttribute QuickBookingEntity quickBooking, HttpSession session) {
//        CustomersEntity loggedInCustomer = (CustomersEntity) session.getAttribute("loggedInCustomer");
//
//        if (loggedInCustomer != null) {
//            quickBooking.setCustomer(loggedInCustomer);
//        }
//
//        quickBooking.setCreatedAt(LocalDateTime.now());
//        quickBooking.setCategory("Quick");
//
//        quickBookingRepository.save(quickBooking);
//        return "pandaone/quickBookings/quickBookingSuccess";
//    }