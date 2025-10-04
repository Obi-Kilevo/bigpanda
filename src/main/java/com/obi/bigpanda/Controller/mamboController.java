package com.obi.bigpanda.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/hi")
public class mamboController {

//    @GetMapping
//    public String showme() {
//        return "test/me";
//    }

    @GetMapping("/m")
    public String manu() {
        return "test2/you";

    }
    @GetMapping("/hii")
    public String showhii() {
        return "test/hii";
    }

    @GetMapping("/kikuletwa")
    public String showme2() {
        return "test/kikuletwa";
    }
    @GetMapping("/ngorongoro")
    public String showm2() {
        return "test/ngorongoro";
    }

    @GetMapping("/mkomazi")
    public String showmw2() {
        return "test/mkomazi";
    }

    @GetMapping("/kilimanjaro")
    public String show2() {
        return "test/kilimanjaro";
    }

    @GetMapping("/manyara")
    public String showi2() {
        return "test/manyara";
    }

    @GetMapping("/olduvai")
    public String showi23() {
        return "test/olduvai";
    }

    @GetMapping("/arusha")
    public String show() {
        return "test/arusha";
    }

    @GetMapping("/tarangire")
    public String show1() {
        return "test/tarangire";
    }

    @GetMapping("/ruaha")
    public String show3() {
        return "test/ruaha";
    }

    @GetMapping("/serengeti")
    public String show4() {
        return "test/serengeti";
    }
}