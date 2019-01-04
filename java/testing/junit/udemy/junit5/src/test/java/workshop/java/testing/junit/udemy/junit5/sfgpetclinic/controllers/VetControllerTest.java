package workshop.java.testing.junit.udemy.junit5.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import workshop.java.testing.junit.udemy.junit5.sfgpetclinic.fauxspring.ModelMapImpl;
import workshop.java.testing.junit.udemy.junit5.sfgpetclinic.model.Vet;
import workshop.java.testing.junit.udemy.junit5.sfgpetclinic.services.SpecialtyService;
import workshop.java.testing.junit.udemy.junit5.sfgpetclinic.services.VetService;
import workshop.java.testing.junit.udemy.junit5.sfgpetclinic.services.map.SpecialityMapService;
import workshop.java.testing.junit.udemy.junit5.sfgpetclinic.services.map.VetMapService;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class VetControllerTest {

    private VetService vetService;
    private SpecialtyService specialtyService;

    private VetController vetController;

    @BeforeEach
    void setUp() {
        specialtyService = new SpecialityMapService();
        vetService = new VetMapService(specialtyService);

        vetController = new VetController(vetService);

        Vet vet1 = new Vet(1L, "Joe", "Buck", null);
        Vet vet2 = new Vet(2L, "Jimmy", "Smith", null);

        vetService.save(vet1);
        vetService.save(vet2);
    }

    @Test
    void listVets() {
        ModelMapImpl model = new ModelMapImpl();

        String view = vetController.listVets(model);

        assertThat("vets/index").isEqualTo(view);

        Set modelAttribute = (Set) model.getMap().get("vets");
        assertThat(modelAttribute.size()).isEqualTo(2);
    }
}
