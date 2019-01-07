package workshop.java.testing.junit.udemy.junit5.sfgpetclinic;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class InlineMockTestTest {

    @Test
    void testInitMock() {
        Map mapMock = mock(Map.class);

        assertEquals(0, mapMock.size());
    }

}
