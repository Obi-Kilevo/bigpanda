package com.obi.bigpanda.Controller;

import com.obi.bigpanda.Entity.PandaThreeEntity;
import com.obi.bigpanda.Repository.PandaThreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/boxthree")
public class PandaThreeController {

    @Autowired
    private PandaThreeRepository repository;

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("pandaThreeEntity", new PandaThreeEntity());
        return "boxthree/index";
    }

    @PostMapping("/submit")
    public String submitForm(@ModelAttribute PandaThreeEntity pandaThreeEntity) {
//        pandaThreeEntity.setRegistrationDate(LocalDate.now());
        repository.save(pandaThreeEntity);
        return "redirect:/boxthree/success";
    }

    @GetMapping("/success")
    public String showSuccess() {
        return "boxthree/success";
    }

    @GetMapping("/submissions")
    public String showSubmissions(Model model) {
        List<PandaThreeEntity> submissions = repository.findAll();
        model.addAttribute("submissions", submissions);
        return "boxthree/submissions";
    }
}