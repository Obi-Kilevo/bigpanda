package com.obi.bigpanda.Controller;

import com.obi.bigpanda.Entity.CustomersEntity;
import com.obi.bigpanda.Repository.CustomersRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/bone")
public class PandaOneController {

    private final CustomersRepository customersRepository;

    public PandaOneController(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

        @GetMapping
    public String showLanguagePage(Model model) {
        model.addAttribute("languages", new String[] {"swahili", "english", "chinese"});
        return "boxone/languageSelection";  // template to display languages
    }






    @GetMapping("/home")
    public String showHomePage(Model model, HttpSession session) {
        Long customerId = (Long) session.getAttribute("loggedInCustomerId");

        if (customerId != null) {
            Optional<CustomersEntity> customer = customersRepository.findById(customerId);
            if (customer.isPresent()) {
                model.addAttribute("nickname", customer.get().getNickname());
                model.addAttribute("isRegistered", true);
                return "home";
            }
        }

        // No user logged in
        model.addAttribute("isRegistered", false);
        return "home";
    }
}
