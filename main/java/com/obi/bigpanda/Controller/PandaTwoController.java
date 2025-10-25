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

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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

        // 1️⃣ Set locale
        Locale locale;
        if (lang != null) {
            switch(lang) {
                case "zh": locale = Locale.CHINESE; break;
                case "sw": locale = new Locale("sw"); break;
                case "de": locale = Locale.GERMAN; break;
                case "sv": locale = new Locale("sv"); break;
                default:   locale = Locale.ENGLISH; break;
            }
        } else {
            locale = Locale.ENGLISH;
            lang = "en";
        }
        localeResolver.setLocale(request, response, locale);
        model.addAttribute("currentLang", lang);

        // 2️⃣ Build labels map based on current language
        Map<String, String> labels = new HashMap<>();
        switch(lang) {
            case "sw": // Swahili
                labels.put("nickname", "Chagua Jina la Utambulisho *");
                labels.put("nicknamePlaceholder", "Weka jina lako la kipekee (herufi 2+)");
                labels.put("password", "Tengeneza Nenosiri *");
                labels.put("passwordPlaceholder", "Tumia herufi kubwa, ndogo, namba na alama maalum");
                labels.put("registerBtn", "Jisajili Sasa");
                break;
            case "zh": // Chinese
                labels.put("nickname", "选择昵称 *");
                labels.put("nicknamePlaceholder", "输入唯一昵称（至少2个字符）");
                labels.put("password", "创建密码 *");
                labels.put("passwordPlaceholder", "使用大写、小写、数字和特殊字符");
                labels.put("registerBtn", "立即注册");
                break;
            case "de": // German
                labels.put("nickname", "Wähle deinen Spitznamen *");
                labels.put("nicknamePlaceholder", "Gib einen eindeutigen Spitznamen ein (2+ Zeichen)");
                labels.put("password", "Passwort erstellen *");
                labels.put("passwordPlaceholder", "Verwende Großbuchstaben, Kleinbuchstaben, Zahl & Sonderzeichen");
                labels.put("registerBtn", "Jetzt registrieren");
                break;
            case "sv": // Swedish
                labels.put("nickname", "Välj ditt smeknamn *");
                labels.put("nicknamePlaceholder", "Ange ett unikt smeknamn (2+ tecken)");
                labels.put("password", "Skapa lösenord *");
                labels.put("passwordPlaceholder", "Använd stora, små bokstäver, nummer & specialtecken");
                labels.put("registerBtn", "Registrera nu");
                break;
            default: // English
                labels.put("nickname", "Choose Your Nickname *");
                labels.put("nicknamePlaceholder", "Enter a unique nickname (2+ characters)");
                labels.put("password", "Create Password *");
                labels.put("passwordPlaceholder", "Use uppercase, lowercase, number & special character");
                labels.put("registerBtn", "Register Now");


        }
        model.addAttribute("labels", labels);

        // 3️⃣ Existing attributes
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
