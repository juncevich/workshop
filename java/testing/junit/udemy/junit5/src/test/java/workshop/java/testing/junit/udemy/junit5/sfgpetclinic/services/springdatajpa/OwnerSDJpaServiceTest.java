package workshop.java.testing.junit.udemy.junit5.sfgpetclinic.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import workshop.java.testing.junit.udemy.junit5.sfgpetclinic.model.Owner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled(value = "Disabled until we learn Mocking")
class OwnerSDJpaServiceTest {

    private OwnerSDJpaService service;

    @BeforeEach
    void setUp() {
        service = new OwnerSDJpaService(null, null, null);
    }

    @Disabled
    @Test
    void findByLastName() {
        Owner foundOwner = service.findByLastName("Buck"); 
        assertNotNull(foundOwner);
    }

    @Test
    void findAllByLastNameLike() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }
}
