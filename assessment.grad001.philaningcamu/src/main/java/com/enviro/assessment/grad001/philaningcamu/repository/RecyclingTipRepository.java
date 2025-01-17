package com.enviro.assessment.grad001.philaningcamu.repository;


import com.enviro.assessment.grad001.philaningcamu.model.entity.RecyclingTip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecyclingTipRepository extends JpaRepository<RecyclingTip, Long> {
    List<RecyclingTip> findByWasteItemId(Long wasteItemId);
    @Query("SELECT rt FROM RecyclingTip rt WHERE rt.wasteItem.category.id = :categoryId")
    List<RecyclingTip> findPublishedTipsByCategory(@Param("categoryId") Long categoryId);

}
