package de.grabduck.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by alex on 23.01.17.
 */
@Data @AllArgsConstructor @NoArgsConstructor @Document public class StackoverflowWebSite {

    @Id private String id;

    private String website;

    private String iconImageUrl;

    private String title;

    private String description;

}
