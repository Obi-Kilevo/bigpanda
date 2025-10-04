package com.obi.bigpanda.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ten") // All handler methods in this class will be under the "/ten" path:cite[4]:cite[9]
public class ParksController {

    // The full path for this is now: /ten/m
    @GetMapping // Handles GET requests to the "/m" path under the class-level "/ten":cite[1]:cite[10]
    public String manu() {
        return "test/me"; // Returns your Thymeleaf or JSP view for the 10 parks
    }

}

