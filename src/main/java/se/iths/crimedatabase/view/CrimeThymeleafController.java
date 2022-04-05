package se.iths.crimedatabase.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import se.iths.crimedatabase.entity.Address;
import se.iths.crimedatabase.entity.Category;
import se.iths.crimedatabase.entity.Crime;
import se.iths.crimedatabase.entity.Criminal;
import se.iths.crimedatabase.service.AddressService;
import se.iths.crimedatabase.service.CategoryService;
import se.iths.crimedatabase.service.CrimeService;
import se.iths.crimedatabase.service.CriminalService;

@Controller
public class CrimeThymeleafController {

    private final CrimeService service;
    private final CategoryService categoryService;
    private final AddressService addressService;
    private final CriminalService criminalService;


    @Autowired
    public CrimeThymeleafController(CrimeService service, CategoryService categoryService, AddressService addressService, CriminalService criminalService) {
        this.service = service;
        this.categoryService = categoryService;
        this.addressService = addressService;
        this.criminalService = criminalService;
    }

    @GetMapping("/addCrimeForm")
    public ModelAndView addCrimeForm() {
        ModelAndView mav = new ModelAndView("add-crime-form");
        Iterable<Category> listCategories = categoryService.findAll();
        Iterable<Address> listAddresses = addressService.findAll();
        Iterable<Criminal> listCriminals = criminalService.findAll();
        Crime newCrime = new Crime();
        mav.addObject("crime", newCrime);
        mav.addObject("listCategories",listCategories);
        mav.addObject("listAddresses",listAddresses);
        mav.addObject("listCriminals",listCriminals);
        return mav;
    }

    @GetMapping("/crimes")
    public ModelAndView showCrimes() {
        ModelAndView mav = new ModelAndView("list-crimes");
        Iterable<Crime> allCrimes = service.findAll();
        mav.addObject("crimes", allCrimes);
        return mav;
    }

    @PostMapping("/saveCrime")
    public String saveCrime(@ModelAttribute Crime crime) {
        service.create(crime);
        return "redirect:/crimes";
    }

    @GetMapping("/crimeUpdateForm")
    public ModelAndView showCrimeUpdateForm(@RequestParam Long id) {
        ModelAndView mav = new ModelAndView("add-crime-form");
        Crime crime = service.findById(id).orElseThrow();
        mav.addObject("crime", crime);
        return mav;
    }

    @GetMapping("/deleteCrime")
    public String deleteCrime(@RequestParam Long id) {
        service.delete(id);
        return "redirect:/crimes";
    }


}
