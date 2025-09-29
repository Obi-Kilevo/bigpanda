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
@RequestMapping("/parkspanda")
public class NationalParksController {


    @Autowired  // ← Add this annotation
    private SessionLocaleResolver localeResolver;


@GetMapping("/gorge")
public String showGorgeForm(@RequestParam(name="lang", required=false) String lang,
                            HttpServletRequest request,
                            HttpServletResponse response,
                            Model model) {

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
        // Get current locale from session
        Locale currentLocale = localeResolver.resolveLocale(request);
        model.addAttribute("currentLang", currentLocale.getLanguage());
    }

    return "olduvai/gorge";
}






    @GetMapping("/kilimanjaro")
    public String showKilimanjaro(@RequestParam(name="lang", required=false) String lang,
                                  HttpServletRequest request,
                                  HttpServletResponse response,
                                  Model model) {

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
            Locale currentLocale = localeResolver.resolveLocale(request);
            model.addAttribute("currentLang", currentLocale.getLanguage());
        }

        return "kilimanjaro/parks";
    }

    @GetMapping("/arusha")
    public String showArusha(Model model) {
        return "arusha/parks";
    }

    @GetMapping("/serengeti")
    public String showSerengeti(Model model) {
        return "serengeti/parks";
    }

    @GetMapping("/tarangire")
    public String showTarangire(Model model) {
        return "tarangire/parks";
    }

    @GetMapping("/kikuletwa")
    public String showKikuletwa(Model model) {
        return "kikuletwa/parks";
    }

    @GetMapping("/mkomazi")
    public String showMkomazi(Model model) {
        return "mkomazi/parks";
    }

    @GetMapping("/ruaha")
    public String showRuaha(Model model) {
        return "ruaha/parks";
    }

    @GetMapping("/ngorongoro")
    public String showNgorongoro(Model model) {
        return "ngorongoro/parks";
    }


    @GetMapping("/manyara")
    public String showManyara(Model model) {
        return "manyara/parks";
    }

//      other contents
    @GetMapping("/one")
    private String showOne(Model model) {
        return "playone/one";
    }

    @GetMapping("/two")
    private String showTwo(Model model) {
        return "playtwo/honeymoons";
    }

    @GetMapping("/three")
    private String showThree(Model model) {
        return "playthree/family";
    }

    @GetMapping("/four")
    private String showfour(Model model) {
        return "playfour/daytrip";
    }

    @GetMapping("/five")
    private String showfive(Model model) {
        return "playfive/celebration";
    }
}



