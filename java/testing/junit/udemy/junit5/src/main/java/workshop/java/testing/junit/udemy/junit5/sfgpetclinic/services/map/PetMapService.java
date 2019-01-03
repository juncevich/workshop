package workshop.java.testing.junit.udemy.junit5.sfgpetclinic.services.map;

import workshop.java.testing.junit.udemy.junit5.sfgpetclinic.model.Pet;
import workshop.java.testing.junit.udemy.junit5.sfgpetclinic.services.PetService;

import java.util.Set;


public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {
    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
