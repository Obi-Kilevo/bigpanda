package com.obi.bigpanda.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/hi")
public class mamboController {

    @GetMapping
    public String showme() {
        return "test/me";
    }

    @GetMapping("hi")
    public String manu() {
        return "test2/you";

    }
}