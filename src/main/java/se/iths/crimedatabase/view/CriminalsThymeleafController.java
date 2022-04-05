package se.iths.crimedatabase.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import se.iths.crimedatabase.entity.Criminal;
import se.iths.crimedatabase.service.CriminalService;

@Controller
public class CriminalsThymeleafController {

    private final CriminalService service;

    @Autowired
    public CriminalsThymeleafController(CriminalService service) {
        this.service = service;
    }

   @GetMapping("/criminals")
    public ModelAndView showCriminals() {
        ModelAndView mav = new ModelAndView("list-criminals");
        Iterable<Criminal> allCriminals = service.findAll();
        mav.addObject("criminals", allCriminals);
        return mav;
    }

    @GetMapping("/addCriminalForm")
    public ModelAndView addCategoryForm() {
        ModelAndView mav = new ModelAndView("add-criminal-form");
        Criminal newCriminal = new Criminal();
        mav.addObject("criminal", newCriminal);
        return mav;
    }

    @PostMapping("/saveCriminal")
    public String saveCriminal(@ModelAttribute Criminal criminal) {
        service.create(criminal);
        return "redirect:/criminals";
    }

    @GetMapping("/criminalUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long id) {
        ModelAndView mav = new ModelAndView("add-criminal-form");
        Criminal criminal = service.findById(id).orElseThrow();
        mav.addObject("criminal", criminal);
        return mav;
    }

    @GetMapping("/deleteCriminal")
    public String deleteCriminal(@RequestParam Long id) {
        service.delete(id);
        return "redirect:/criminals";
    }

}
