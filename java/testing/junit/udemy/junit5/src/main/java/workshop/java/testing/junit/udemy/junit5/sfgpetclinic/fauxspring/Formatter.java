package workshop.java.testing.junit.udemy.junit5.sfgpetclinic.fauxspring;

import workshop.java.testing.junit.udemy.junit5.sfgpetclinic.model.PetType;

import java.text.ParseException;
import java.util.Locale;


public interface Formatter<T> {

    String print(PetType petType, Locale locale);

    PetType parse(String text, Locale locale) throws ParseException;
}
