package com.obi.bigpanda.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/un")
public class UnfinishedWork {

    @GetMapping("/work")
    public String howunfinished() {
//        return "/unfinished/work";
//        return "/bookings/form";
        return "redirect:/bookings/form";

    }
}
