package com.obi.bigpanda.Controller;

import com.obi.bigpanda.Entity.CustomersEntity;
import com.obi.bigpanda.Repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/submit")
    public String submitForm(@ModelAttribute("customer") CustomersEntity customer, Model model) {
        customer.setCreatedAt(java.time.LocalDateTime.now());
        customersRepository.save(customer);
        model.addAttribute("customer", customer);
        return "customers/success";
    }
}
