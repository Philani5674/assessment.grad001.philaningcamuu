package com.enviro.assessment.grad001.philaningcamu.model.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class WasteCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<WasteItem> wasteItems = new HashSet<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<DisposalGuideline> disposalGuidelines = new HashSet<>();

    public Long getId() {
        return id;
    }

    public Set<DisposalGuideline> getDisposalGuidelines() {
        return disposalGuidelines;
    }

    public Set<WasteItem> getWasteItems() {
        return wasteItems;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDisposalGuidelines(Set<DisposalGuideline> disposalGuidelines) {
        this.disposalGuidelines = disposalGuidelines;
    }

    public void setWasteItems(Set<WasteItem> wasteItems) {
        this.wasteItems = wasteItems;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }
}


