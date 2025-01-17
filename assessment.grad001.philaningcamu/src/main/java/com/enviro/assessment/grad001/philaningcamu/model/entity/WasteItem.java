package com.enviro.assessment.grad001.philaningcamu.model.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class WasteItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private WasteCategory category;

    @OneToMany(mappedBy = "wasteItem", cascade = CascadeType.ALL)
    private Set<RecyclingTip> recyclingTips = new HashSet<>();

    @ElementCollection
    @CollectionTable(
            name = "waste_item_keywords",
            joinColumns = @JoinColumn(name = "waste_item_id")
    )

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public WasteCategory getCategory() {
        return category;
    }

    public Set<RecyclingTip> getRecyclingTips() {
        return recyclingTips;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRecyclingTips(Set<RecyclingTip> recyclingTips) {
        this.recyclingTips = recyclingTips;
    }

    public void setCategory(WasteCategory category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }
}