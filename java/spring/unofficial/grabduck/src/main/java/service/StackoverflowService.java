package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.StackoverflowWebSite;
import persistence.StackoverflowWebsiteRepository;

/**
 * Created by alex on 23.01.17.
 */
@Service public class StackoverflowService {

    @Autowired private StackoverflowWebsiteRepository repository;

    public List<StackoverflowWebSite> findAll() {

        return repository.findAll();
    }
}
