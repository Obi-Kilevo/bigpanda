package com.obi.bigpanda.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/belt")
public class UpperBeltController {

    @GetMapping
    public String showUpper() {
        return "belt/upper";
    }
}
