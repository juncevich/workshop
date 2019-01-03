package workshop.java.testing.junit.udemy.junit5.sfgpetclinic.fauxspring;


public interface BindingResult {
    void rejectValue(String lastName, String notFound, String not_found);

    boolean hasErrors();
}
