package model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by alex on 23.01.17.
 */
@Document public class StackoverflowWebSite {

    @Id private final String id;

    private final String website;

    private final String iconImageUrl;

    private final String title;

    private final String description;

    public StackoverflowWebSite(String id, String website, String iconImageUrl, String title,
            String description) {

        this.id = id;
        this.website = website;
        this.iconImageUrl = iconImageUrl;
        this.title = title;
        this.description = description;
    }

    /**
     * @return the {@link #id}
     */
    public String getId() {

        return id;
    }

    /**
     * @return the {@link #website}
     */
    public String getWebsite() {

        return website;
    }

    /**
     * @return the {@link #iconImageUrl}
     */
    public String getIconImageUrl() {

        return iconImageUrl;
    }

    /**
     * @return the {@link #title}
     */
    public String getTitle() {

        return title;
    }

    /**
     * @return the {@link #description}
     */
    public String getDescription() {

        return description;
    }
}
