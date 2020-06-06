package com.example.intro

import com.example.intro.domain.Person
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class IntroToSpringBootApplicationTests {

    @Autowired
    lateinit var template: TestRestTemplate

    @Test
    @Order(1)
    fun testAdd() {
        val person = Person(1, "John", "Smith", 40)
        val entity = HttpEntity<Person>(person)
        val response = template.exchange("/persons", HttpMethod.POST, entity, Void::class.java)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    @Order(2)
    fun testFindById() {
        val person = template.getForObject("/persons/{id}", Person::class.java, 1)
        Assertions.assertNotNull(person)
        Assertions.assertEquals(1, person.id)
    }

    @Test
    @Order(3)
    fun testUpdate() {
        val person = Person(1, "John", "Smith", 40)
        val entity = HttpEntity<Person>(person)
        val response = template.exchange("/persons", HttpMethod.PUT, entity, Void::class.java)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    @Order(4)
    fun testDelete() {

        template.delete("/persons", 1)

    }

}
