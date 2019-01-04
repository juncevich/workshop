package workshop.java.testing.junit.udemy.junit5.sfgpetclinic.services.map;

import workshop.java.testing.junit.udemy.junit5.sfgpetclinic.model.Speciality;
import workshop.java.testing.junit.udemy.junit5.sfgpetclinic.model.Vet;
import workshop.java.testing.junit.udemy.junit5.sfgpetclinic.services.SpecialtyService;
import workshop.java.testing.junit.udemy.junit5.sfgpetclinic.services.VetService;

import java.util.Set;

public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialtyService specialtyService;

    public VetMapService(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {

        if (object.getSpecialities() != null && !object.getSpecialities().isEmpty()) {
            object.getSpecialities().forEach(speciality -> {
                if (speciality.getId() == null) {
                    Speciality savedSpecialty = specialtyService.save(speciality);
                    speciality.setId(savedSpecialty.getId());
                }
            });
        }

        return super.save(object);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
