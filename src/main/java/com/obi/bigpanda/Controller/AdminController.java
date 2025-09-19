//package com.obi.bigpanda.Controller;
//
//import com.obi.bigpanda.Entity.AdminEntity;
//import com.obi.bigpanda.Entity.BookingEntity;
//import com.obi.bigpanda.Repository.AdminRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//
//    @Autowired
//    private AdminRepository adminRepository;
//
////    @GetMapping("/dashboard")
////    public String adminDashboard(Model model) {
////        List<AdminEntity> parks = adminRepository.findAll();
////        model.addAttribute("parks", parks);
////        return "pandaone/admincontroller/dashboard";
////    }
//
//
//    @GetMapping("/dashboard")
//    public String adminDashboard(Model model) {
//        List<AdminEntity> parks = adminRepository.findAll();
//        if (parks == null) {
//            parks = new ArrayList<>();
//        }
//        model.addAttribute("parks", parks);
//        return "pandaone/admincontroller/dashboard";
//    }
//
//    @GetMapping("/add-park")
//    public String showAddParkForm(Model model) {
//        model.addAttribute("park", new AdminEntity());
//        return "pandaone/admincontroller/add-park";
//    }
//
//    @PostMapping("/add-park")
//    public String addPark(@ModelAttribute AdminEntity park) {
//        adminRepository.save(park);
//        return "redirect:/admin/dashboard";
//    }
//
//    @GetMapping("/edit-park/{id}")
//    public String showEditParkForm(@PathVariable Long id, Model model) {
//        Optional<AdminEntity> park = adminRepository.findById(id);
//        park.ifPresent(value -> model.addAttribute("park", value));
//        return "pandaone/admincontroller/edit-park";
//    }
//
//    @PostMapping("/edit-park/{id}")
//    public String editPark(@PathVariable Long id, @ModelAttribute AdminEntity updatedPark) {
//        updatedPark.setId(id);
//        adminRepository.save(updatedPark);
//        return "redirect:/admin/dashboard";
//    }
//
//    @GetMapping("/delete-park/{id}")
//    public String deletePark(@PathVariable Long id) {
//        adminRepository.deleteById(id);
//        return "redirect:/admin/dashboard";
//    }
//
//    @GetMapping("/bookings/new/{parkId}")
//    public String showBookingForm(@PathVariable Long parkId, Model model) {
//        AdminEntity park = adminRepository.findById(parkId).orElse(null);
//        model.addAttribute("park", park);
//        model.addAttribute("booking", new BookingEntity());
//        return "pandaone/bookings/new-booking"; // must match your template path
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
@RequestMapping("/admin")
public class AdminController {

    private final AdminRepository adminRepository;
    private final BookingRepository bookingRepository;

    public AdminController(AdminRepository adminRepository, BookingRepository bookingRepository) {
        this.adminRepository = adminRepository;
        this.bookingRepository = bookingRepository;
    }

    // Show all tours
    @GetMapping("/tours")
    public String showAllTours(Model model) {
//        List<AdminEntity> tours = adminRepository.findAll();
        List<AdminEntity> tours = adminRepository.findAllActiveTours();
        model.addAttribute("tours", tours);
        return "pandaone/admin/tours";
    }
//    @GetMapping("/tours/restore/{id}")
//    public String restoreTour(@PathVariable Long id) {
//        adminRepository.restoreById(id);
//        return "redirect:/admin/tours";
//    }


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

    // Add new tour
//    @PostMapping("/tours/add")
//    public String addTour(@ModelAttribute AdminEntity tour) {
//        adminRepository.save(tour);
//        return "redirect:/admin/tours";
//    }


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

    // Update tour
//    @PostMapping("/tours/update/{id}")
//    public String updateTour(@PathVariable Long id, @ModelAttribute AdminEntity updatedTour) {
//        updatedTour.setId(id);
//        adminRepository.save(updatedTour);
//        return "redirect:/admin/tours";
//    }

    @PostMapping("/tours/update/{id}")
    public String updateTour(@PathVariable Long id, @ModelAttribute AdminEntity updatedTour) {
        AdminEntity existingTour = adminRepository.findById(id).orElse(null);

        // Preserve original createdAt, update timestamp
        updatedTour.setCreatedAt(existingTour.getCreatedAt());
        updatedTour.setUpdatedAt(LocalDateTime.now());

        adminRepository.save(updatedTour);
        return "redirect:/admin/tours";
    }


    // Delete tour
//    @GetMapping("/tours/delete/{id}")
//    public String deleteTour(@PathVariable Long id) {
//        adminRepository.deleteById(id);
//        return "redirect:/admin/tours";
//    }


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
}