package com.obi.bigpanda.Controller;

import com.obi.bigpanda.Entity.CustomersEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bone")
public class PandaOneController {

//    @GetMapping
//    public String showLanguagePage(Model model) {
//        model.addAttribute("languages", new String[] {"swahili", "english", "chinese"});
//        return "boxone/languageSelection";  // template to display languages
//    }
    @GetMapping
    public String showLanguagePage(Model model) {
        model.addAttribute("languages", new String[] {"swahili", "english", "chinese"});
        model.addAttribute("customer", new CustomersEntity()); // Add this line
        return "boxone/languageSelection";
    }

    @GetMapping("/home")
    public String showHomepage() {
        return "boxone/languageSelection"; // your homepage
    }
}
