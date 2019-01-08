package workshop.java.testing.junit.udemy.junit5.sfgpetclinic.services.springdatajpa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import workshop.java.testing.junit.udemy.junit5.sfgpetclinic.model.Speciality;
import workshop.java.testing.junit.udemy.junit5.sfgpetclinic.repositories.SpecialtyRepository;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {
    @Mock
    private SpecialtyRepository specialtyRepository;

    @InjectMocks
    private SpecialitySDJpaService service;

    @Test
    void deleteById() {
        service.deleteById(1L);
    }

    @Test
    void delete() {
        service.delete(new Speciality());
    }

}
