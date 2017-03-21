package com.apress.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringBootJournalSecureApplication.class)
@WebAppConfiguration
public class SpringBootJournalSecureApplicationTests {

	@Test
	public void contextLoads() {
	}

}
