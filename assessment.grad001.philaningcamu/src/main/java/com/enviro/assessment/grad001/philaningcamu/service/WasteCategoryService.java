package com.enviro.assessment.grad001.philaningcamu.service;

import com.enviro.assessment.grad001.philaningcamu.model.entity.WasteCategory;
import com.enviro.assessment.grad001.philaningcamu.model.entity.WasteItem;
import com.enviro.assessment.grad001.philaningcamu.repository.WasteCategoryRepository;
import com.enviro.assessment.grad001.philaningcamu.repository.WasteItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WasteCategoryService {
    private final WasteCategoryRepository categoryRepository;
    private final WasteItemRepository itemRepository;

    @Autowired
    public WasteCategoryService(WasteCategoryRepository categoryRepository, WasteItemRepository itemRepository) {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
    }

    public WasteCategory createCategory(WasteCategory category) {
        System.out.println(category.getName() +" "+ category.getDescription());
        return categoryRepository.save(category);
    }

    public List<WasteCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<WasteCategory> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public List<WasteItem> getItemsByCategory(Long categoryId) {
        return itemRepository.findByCategoryId(categoryId);
    }

    public boolean deleteCategory(Long id) {
        if(categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
