package com.obi.bigpanda.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("bar")
public class CustomersWindowController {


    @GetMapping
    public String getEmAll() {
        return "customersWindow/bar";
    }
}
