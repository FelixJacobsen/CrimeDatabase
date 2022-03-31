package se.iths.crimedatabase.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import se.iths.crimedatabase.entity.Crime;
import se.iths.crimedatabase.entity.Victim;
import se.iths.crimedatabase.service.VictimService;

@Controller
public class VictimThymeleafController {

    private final VictimService service;

    public VictimThymeleafController(VictimService service) {
        this.service = service;
    }

    @GetMapping("/showVictims")
    public ModelAndView showVictims() {
        ModelAndView mav = new ModelAndView("list-victims");
        Iterable<Victim> allVictims = service.findAll();
        mav.addObject("victims",allVictims);
        return mav;
    }

    @GetMapping("/addVictimForm")
    public ModelAndView addVictimForm() {
        ModelAndView mav = new ModelAndView("add-victim-form");
        Victim newVictim = new Victim();
        mav.addObject("victim",newVictim);
        return mav;
    }

    @PostMapping("/saveVictim")
    public String saveVictim(@ModelAttribute Victim victim) {
        service.create(victim);
        return "redirect:/showVictims";
    }

    @GetMapping("/showVictimUpdateForm")
    public ModelAndView showVictimUpdateForm(@RequestParam Long id) {
        ModelAndView mav = new ModelAndView("add-victim-form");
        Victim victim = service.findById(id).orElseThrow();
        mav.addObject("victim",victim);
        return mav;
    }

    @GetMapping("/deleteVictim")
    public String deleteVictim(@RequestParam Long id) {
        service.delete(id);
        return "redirect:/showVictims";
    }
}
