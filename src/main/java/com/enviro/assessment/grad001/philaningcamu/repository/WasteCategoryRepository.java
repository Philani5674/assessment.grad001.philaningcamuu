package com.enviro.assessment.grad001.philaningcamu.repository;

import com.enviro.assessment.grad001.philaningcamu.model.entity.WasteCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WasteCategoryRepository extends JpaRepository<WasteCategory, Long> {
    Optional<WasteCategory> findByNameIgnoreCase(String name);
    List<WasteCategory> findByNameContainingIgnoreCase(String namePattern);
}

