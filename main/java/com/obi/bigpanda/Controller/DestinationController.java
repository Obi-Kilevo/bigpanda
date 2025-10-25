package com.obi.bigpanda.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dest")
public class DestinationController {

//   huyu ngorongoro ndio mbeba wa national parks zote hapa chini, ndio king
    @RequestMapping("/ngorongoro")
    public String showNgorongoro() {
        return "destinationone/ngorongoro";
    }

//     kwahiyo hapa inashirikiana na mama form
    @RequestMapping("/ngorongoroform")
    public String showDestinationoneasonethinginmamaform() {
        return "destinationoneasonethininmamaform/ngorongoro";
    }






    @RequestMapping("/ngorongoroone")
    public String showngorongoropicture() {
        return "ngorongoroone/parks";
    }


    @RequestMapping("/serengetione")
    public String showsere() {
        return "serengetione/parks";
    }

    @RequestMapping("/manyaraone")
    public String showmanyaraone() {
        return "manyaraone/parks";
    }

    @RequestMapping("/tarangireone")
    public String showtarangireone() {
        return "tarangireone/parks";
    }

    @RequestMapping("/ruahaone")
    public String showruahaone() {
        return "ruahaone/parks";
    }

    @RequestMapping("/mikumione")
    public String showmikumione() {
        return "mikumione/parks";
    }

    @RequestMapping("/arushaone")
    public String showarushaone() {
        return "arushaone/parks";
    }

    @RequestMapping("/kikuletwaone")
    public String showkikuletwaone() {
        return "kikuletwaone/parks";
    }

    @RequestMapping("/kilimanjaroone")
    public String showkilimanjaroone() {
        return "kilimanjaroone/parks";
    }

    @RequestMapping("/olduvaione")
    public String showolduvaione() {
        return "olduvaione/gorge";
    }

    @RequestMapping("/mkomazione")
    public String showmkomazione() {
        return "mkomazione/parks";
    }

    @RequestMapping("/kid")
    public String showmkid() {
        return "child/kid";
    }

    @RequestMapping("/about")
    public String shows8() {
        return"us";
    }
}


