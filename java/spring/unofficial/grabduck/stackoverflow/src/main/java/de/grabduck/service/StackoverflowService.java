package de.grabduck.service;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;

import de.grabduck.model.SiteDto;
import de.grabduck.model.StackoverflowWebSite;
import de.grabduck.persistence.StackoverflowWebsiteRepository;
import lombok.NonNull;

/**
 * Created by alex on 23.01.17.
 */
@Service public class StackoverflowService {

    @Autowired private StackExchangeClient stackExchangeClient;

    @Autowired private StackoverflowWebsiteRepository repository;

    public List<StackoverflowWebSite> findAll() {

        return stackExchangeClient.getSites().stream().map(this::toStackOverflovWebsite)
                .filter(this::ignoreMeta)
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));
    }

    private boolean ignoreMeta(@NonNull StackoverflowWebSite stackoverflowWebSite) {

        return !stackoverflowWebSite.getId().startsWith("meta.");
    }

    private StackoverflowWebSite toStackOverflovWebsite(@NonNull SiteDto input) {

        return new StackoverflowWebSite(input.getSite_url()
                .substring("http://".length(), input.getSite_url().length() - ".com".length()),
                input.getSite_url(), input.getFavicon_url(), input.getName(), input.getAudience());
    }
}
