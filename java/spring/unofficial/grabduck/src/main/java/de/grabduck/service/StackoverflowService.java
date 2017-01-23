package de.grabduck.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.grabduck.model.StackoverflowWebSite;
import de.grabduck.persistence.StackoverflowWebsiteRepository;

/**
 * Created by alex on 23.01.17.
 */
@Service public class StackoverflowService {

    private static List<StackoverflowWebSite> items = new ArrayList<>();

    static {
        items.add(new StackoverflowWebSite("stackoverflow", "http://stackoverflow.com",
                "http://cdn.static.net/Sites/stackoverflow/img/favicon.ico",
                "Stack Overflow (StackExchange)", "for proffessional and enthusiast programmers"));
        items.add(new StackoverflowWebSite("serverfault", "http://serverfault.com",
                "http://cdn.static.net/Sites/serverfault/img/favicon.ico",
                "Server Fault (StackExchange)", "for system and network administrators"));
        items.add(new StackoverflowWebSite("superuser", "http://superuser.com",
                "http://cdn.static.net/Sites/superuser/img/favicon.ico",
                "Super User (StackExchange)", "for computer enthusiast ans power users"));

    }

    @Autowired private StackoverflowWebsiteRepository repository;

    @PostConstruct public void init() {

        repository.save(items);
    }

    public List<StackoverflowWebSite> findAll() {

        return repository.findAll();
    }
}
