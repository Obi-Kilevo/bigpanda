//package com.obi.bigpanda.Controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/btwo")
//public class PandaTwoController {
//
//    @GetMapping
//    public String showInfoPage(Model model) {
//        model.addAttribute("contact", "Contact us at info@bigpandasafaris.com");
//        model.addAttribute("pictures", new String[] {
//                "/images/pic1.jpg",
//                "/images/pic2.jpg",
//                "/images/pic3.jpg"
//        });
//        model.addAttribute("travelStrategy", "Our travel strategy is client-focused...");
//        model.addAttribute("adventure", "Explore thrilling adventures with us.");
//        model.addAttribute("travelInsights", "Insider travel tips and insights.");
//        model.addAttribute("whyWeExist", "We exist to connect travelers with authentic experiences.");
//        model.addAttribute("planWithUs", "Plan your dream trip with our expert guides.");
//
//        return "boxtwo/infoPage";  // template in templates/boxtwo/infoPage.html
//    }
//}


package com.obi.bigpanda.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Controller
@RequestMapping("/btwo")
public class PandaTwoController {

    @Autowired
    private SessionLocaleResolver localeResolver;

    @GetMapping
    public String showInfoPage(@RequestParam(name = "lang", required = false) String lang,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               Model model) {

        // Set the locale based on language parameter
        if (lang != null) {
            Locale locale;
            switch(lang) {
                case "zh": locale = Locale.CHINESE; break;
                case "sw": locale = new Locale("sw"); break;
                case "de": locale = Locale.GERMAN; break;
                case "sv": locale = new Locale("sv"); break;
                default:   locale = Locale.ENGLISH; break;
            }
            localeResolver.setLocale(request, response, locale);
            model.addAttribute("currentLang", lang);
        } else {
            model.addAttribute("currentLang", "en"); // default
        }

        // Your existing attributes
        model.addAttribute("contact", "Contact us at info@bigpandasafaris.com");
        model.addAttribute("pictures", new String[] {
                "/images/pic1.jpg",
                "/images/pic2.jpg",
                "/images/pic3.jpg"
        });
        model.addAttribute("travelStrategy", "Our travel strategy is client-focused...");
        model.addAttribute("adventure", "Explore thrilling adventures with us.");
        model.addAttribute("travelInsights", "Insider travel tips and insights.");
        model.addAttribute("whyWeExist", "We exist to connect travelers with authentic experiences.");
        model.addAttribute("planWithUs", "Plan your dream trip with our expert guides.");

        return "boxtwo/infoPage";
    }
}