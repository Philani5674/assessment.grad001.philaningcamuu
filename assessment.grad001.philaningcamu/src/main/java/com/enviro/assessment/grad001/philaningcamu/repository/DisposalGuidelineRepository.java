package com.enviro.assessment.grad001.philaningcamu.repository;

import com.enviro.assessment.grad001.philaningcamu.model.entity.DisposalGuideline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DisposalGuidelineRepository extends JpaRepository<DisposalGuideline, Long> {
    List<DisposalGuideline> findByCategoryId(Long categoryId);
}
