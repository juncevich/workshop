package de.grabduck.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.grabduck.model.StackoverflowWebSite;
import de.grabduck.service.StackoverflowService;

/**
 * Created by alex on 23.01.17.
 */
@RestController
@RequestMapping("/api/stackoverflow")
public class StackoverflowController {

    @Autowired
    StackoverflowService stackoverflowService;

    @RequestMapping
    public List<StackoverflowWebSite> getListOfProviders() {

        return stackoverflowService.findAll();
    }
}
