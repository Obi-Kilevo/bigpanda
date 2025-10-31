//package com.obi.bigpanda.Controller;
//
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/arage")
//public class ArageController {
//
//
//    @GetMapping
//    public String showLanguages() {
//        return "ArageLanguage/select";
//    }
//}


package com.obi.bigpanda.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Controller
@RequestMapping("/arage")
public class ArageController {

    @Autowired
    private SessionLocaleResolver localeResolver; // Reuse the same locale resolver

    // Show the draggable language switcher component (if needed as a standalone page)
    @GetMapping
    public String showLanguages() {
        return "ArageLanguage/select";
    }

    // Core: Handle language switch request from the draggable component
    @GetMapping("/switch-lang")
    public String switchLanguage(@RequestParam("lang") String lang,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        // 1. Map language code to Locale (same as PandaTwoController)
        Locale locale;
        switch(lang) {
            case "zh": locale = Locale.CHINESE; break;
            case "sw": locale = new Locale("sw"); break;
            case "de": locale = Locale.GERMAN; break;
            case "sv": locale = new Locale("sv"); break;
            case "fr-CA": locale = new Locale("fr", "CA"); break; // Add Canadian French
            default: locale = Locale.ENGLISH; break;
        }

        // 2. Update session locale
        localeResolver.setLocale(request, response, locale);

        // 3. Redirect back to the current page (no need to jump to home)
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/btwo"); // Default to main page if referer is null
    }
}