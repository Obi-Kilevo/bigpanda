package com.obi.bigpanda.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bigone")
public class OfficeOneEntity {


    @GetMapping
    private String one() {
        return "pandaone/one";
    }

    @GetMapping("/future")
    private String communications() {
        return "communication/all";
    }


    @GetMapping("/about")
    private String aboutUs() {
        return "aboutUs/today";
    }
}
