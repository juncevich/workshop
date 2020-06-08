package com.example.intro.controller

import com.example.intro.domain.Person
import com.example.intro.repository.PersonRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/persons")
class PersonController(private val repository: PersonRepository) {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): Person? = repository.findById(id)

    @GetMapping
    fun findAll(): MutableList<Person> = repository.findAll()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int) = repository.delete(id)

    @PutMapping
    fun update(@RequestBody person: Person) = repository.update(person)

    @PostMapping
    fun add(@RequestBody person: Person) = repository.add(person)
}
