package com.enviro.assessment.grad001.philaningcamu.service;


import com.enviro.assessment.grad001.philaningcamu.model.entity.DisposalGuideline;
import com.enviro.assessment.grad001.philaningcamu.repository.DisposalGuidelineRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DisposalGuidelineService {
    private final DisposalGuidelineRepository guidelineRepository;

    @Autowired
    public DisposalGuidelineService(DisposalGuidelineRepository guidelineRepository) {
        this.guidelineRepository = guidelineRepository;
    }

    public DisposalGuideline createGuideline(DisposalGuideline guideline) {
        guideline.setCreatedAt(java.sql.Date.valueOf(LocalDate.now()));
        guideline.setUpdatedAt(java.sql.Date.valueOf(LocalDate.now()));
        return guidelineRepository.save(guideline);
    }

    public DisposalGuideline updateGuideline(DisposalGuideline guideline) {
        guideline.setUpdatedAt(java.sql.Date.valueOf(LocalDate.now()));
        return guidelineRepository.save(guideline);
    }

    public List<DisposalGuideline> getGuidelinesByCategory(Long categoryId) {
        return guidelineRepository.findByCategoryId(categoryId);
    }

    public List<DisposalGuideline> getAllGuidelines() {
        return guidelineRepository.findAll();
    }

    public void deleteGuideline(Long id) {
         guidelineRepository.deleteById(id);
    }

    public Optional<DisposalGuideline> getGuidelineById(Long id) {
        return guidelineRepository.findById(id);
    }
}