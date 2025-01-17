package com.enviro.assessment.grad001.philaningcamu.model.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class DisposalGuideline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private WasteCategory category;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String instructions;

    @Column(name = "environmental_impact", columnDefinition = "TEXT")
    private String environmentalImpact;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getEnvironmentalImpact() {
        return environmentalImpact;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getTitle() {
        return title;
    }

    public WasteCategory getCategory() {
        return category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setEnvironmentalImpact(String environmentalImpact) {
        this.environmentalImpact = environmentalImpact;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(WasteCategory category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


}