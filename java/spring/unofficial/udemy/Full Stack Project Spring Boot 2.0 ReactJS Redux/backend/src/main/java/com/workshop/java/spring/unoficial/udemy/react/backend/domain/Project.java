package com.workshop.java.spring.unoficial.udemy.react.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank(message = "Project name is required")
    String projectName;
    @NotBlank(message = "Project identifier is required")
    @Size(min = 4, max = 5, message = "Please use 4 to 5 characters")
    @Column(updatable = false, unique = true)
    String projectIdentifier;
    @NotBlank(message = "Project description is required")
    String description;
    @JsonFormat(pattern = "yyyy-mm-dd")
    Date startDate;
    @JsonFormat(pattern = "yyyy-mm-dd")
    Date endDate;
    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(updatable = false)
    Date createdAt;
    @JsonFormat(pattern = "yyyy-mm-dd")
    Date updatedAt;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Backlog backlog;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}
