package workshop.java.testing.junit.udemy.junit5.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IndexControllerTest {

    IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @Test
    void index() {
        assertEquals("index", controller.index());
    }

    @Test
    void oupsHandler() {
    assertTrue("notimplemented".equals(controller.oupsHandler()), () -> "This is so expensive message");
    }
}
