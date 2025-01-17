package com.enviro.assessment.grad001.philaningcamu.service;

import com.enviro.assessment.grad001.philaningcamu.model.entity.RecyclingTip;
import com.enviro.assessment.grad001.philaningcamu.repository.RecyclingTipRepository;
import com.enviro.assessment.grad001.philaningcamu.repository.WasteItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class RecyclingTipService {
    private final RecyclingTipRepository tipRepository;
    private final WasteItemRepository wasteItemRepository;


    @Autowired
    public RecyclingTipService(
            RecyclingTipRepository tipRepository, WasteItemRepository wasteItemRepository) {
        this.tipRepository = tipRepository;
        this.wasteItemRepository = wasteItemRepository;
    }

    public RecyclingTip createTip(RecyclingTip tip) {
       return tipRepository.save(tip);
    }

    public List<RecyclingTip> getPublishedTipsByCategory(Long categoryId) {
        return tipRepository.findPublishedTipsByCategory(categoryId);
    }

    public List<RecyclingTip> getAllTips() {
        return tipRepository.findAll();
    }

    public void deleteTip(Long id) {
        tipRepository.deleteById(id);
    }

    public boolean getWasteItem(Long wasteItemId) {
        return wasteItemRepository.existsById(wasteItemId);
    }

    public Optional<RecyclingTip> getTipById(Long id) {
        return tipRepository.findById(id);
    }
}
