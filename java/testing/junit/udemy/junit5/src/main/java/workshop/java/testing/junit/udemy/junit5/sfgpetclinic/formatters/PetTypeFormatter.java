package workshop.java.testing.junit.udemy.junit5.sfgpetclinic.formatters;

import workshop.java.testing.junit.udemy.junit5.sfgpetclinic.fauxspring.Formatter;
import workshop.java.testing.junit.udemy.junit5.sfgpetclinic.model.PetType;
import workshop.java.testing.junit.udemy.junit5.sfgpetclinic.services.PetTypeService;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;


public class PetTypeFormatter implements Formatter {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> findPetTypes = petTypeService.findAll();

        for (PetType type : findPetTypes) {
            if (type.getName().equals(text)) {
                return type;
            }
        }

        throw new ParseException("type not found: " + text, 0);
    }
}
