package com.obi.bigpanda.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/beauty")
public class Uremboontroller {


    @RequestMapping("/obiOne")
    public String showUrembo() {
        return "urembo/uremboOne/pandaOne";
    }

    @RequestMapping("/obiTwo")
    public String showUrembo2() {
        return "urembo/uremboTwo/pandaTwo";
    }
    @RequestMapping("/obiFour")
    public String showUrembo4() {
        return "urembo/uremboFour/pandaFour";
    }
    @RequestMapping("/obiFive")
    public String showUrembo5() {
        return "urembo/uremboFive/pandaFive";
    }
    @RequestMapping("/obiSix")
    public String showUrembo6() {
        return "urembo/uremboSix/pandaSix";
    }
    @RequestMapping("/obiSeven")
    public String showUrembo7() {
        return "urembo/uremboSeven/pandaSeven";
    }
    @RequestMapping("/obiEight")
    public String showUrembo8() {
        return "urembo/uremboEight/pandaEight";
    }
    @RequestMapping("/obiNine")
    public String showUrembo9() {
        return "urembo/uremboNine/pandaNine";
    }
    @RequestMapping("/obiTen")
    public String showUrembo10() {
        return "urembo/uremboTen/pandaTen";
    }
    @RequestMapping("/obiEleven")
    public String showUrembo11() {
        return "urembo/uremboEleven/pandaEleven";
    }
    @RequestMapping("/obiTwelve")
    public String showUrembo12() {
        return "urembo/uremboTwelve/pandaTwelve";
    }
}
