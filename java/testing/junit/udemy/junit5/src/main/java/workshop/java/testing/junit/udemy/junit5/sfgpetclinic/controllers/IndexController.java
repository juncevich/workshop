package workshop.java.testing.junit.udemy.junit5.sfgpetclinic.controllers;

public class IndexController {

    public String index() {
        return "index";
    }

    public String oopsHandler() {
        throw new ValueNotFoundException();
    }
}
