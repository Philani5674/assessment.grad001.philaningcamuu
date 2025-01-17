package com.enviro.assessment.grad001.philaningcamu.repository;

import com.enviro.assessment.grad001.philaningcamu.model.entity.WasteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WasteItemRepository extends JpaRepository<WasteItem, Long> {
    List<WasteItem> findByCategoryId(Long categoryId);
}