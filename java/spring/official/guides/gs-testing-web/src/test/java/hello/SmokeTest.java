package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Aleksandr Juncevich
 * on 03 Jan 2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {
    @Autowired
    private
    HomeController controller;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }
}
