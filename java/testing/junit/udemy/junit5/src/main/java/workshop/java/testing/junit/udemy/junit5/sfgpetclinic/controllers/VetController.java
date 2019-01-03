package workshop.java.testing.junit.udemy.junit5.sfgpetclinic.controllers;

import workshop.java.testing.junit.udemy.junit5.sfgpetclinic.fauxspring.Model;
import workshop.java.testing.junit.udemy.junit5.sfgpetclinic.services.VetService;

public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    public String listVets(Model model){

        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }
}
