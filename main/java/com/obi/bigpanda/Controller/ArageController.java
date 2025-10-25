package com.obi.bigpanda.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/arage")
public class ArageController {


    @GetMapping
    public String showLanguages() {
        return "ArageLanguage/select";
    }
}
