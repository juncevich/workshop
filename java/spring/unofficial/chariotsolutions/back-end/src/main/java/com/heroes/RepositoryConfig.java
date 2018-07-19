package com.heroes;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * Repository config
 * 
 * Created by alex on 25.04.17.
 */
@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {

    /**
     * Configure repository
     * 
     * @param config
     *            {@link: #RepositoryRestConfiguration}
     */
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

        config.exposeIdsFor(Hero.class);
    }
}
