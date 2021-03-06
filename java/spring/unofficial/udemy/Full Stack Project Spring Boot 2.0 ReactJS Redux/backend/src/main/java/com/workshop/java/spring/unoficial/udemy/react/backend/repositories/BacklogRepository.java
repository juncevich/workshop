package com.workshop.java.spring.unoficial.udemy.react.backend.repositories;

import com.workshop.java.spring.unoficial.udemy.react.backend.domain.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {
    Backlog findByProjectIdentifier(String identifier);
}
