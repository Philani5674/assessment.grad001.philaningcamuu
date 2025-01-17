package com.enviro.assessment.grad001.philaningcamu.controller;

import com.enviro.assessment.grad001.philaningcamu.model.DTO.WasteCategoryDTO;
import com.enviro.assessment.grad001.philaningcamu.model.DTO.WasteItemDTO;
import com.enviro.assessment.grad001.philaningcamu.model.entity.WasteCategory;
import com.enviro.assessment.grad001.philaningcamu.service.WasteCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;


@RestController
@RequestMapping("/api/v1/categories")
public class WasteCategoryController {
    private final WasteCategoryService categoryService;
    private final ModelMapper modelMapper;

    //WasteCategoryService and ModelMapper objects are injected into the WasteCategoryController class at runtime.
    @Autowired
    public WasteCategoryController(WasteCategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<WasteCategoryDTO> createCategory(@Valid @RequestBody WasteCategoryDTO categoryDTO) {
        WasteCategory category = modelMapper.map(categoryDTO, WasteCategory.class);
        WasteCategory savedCategory = categoryService.createCategory(category);
        if (savedCategory == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }else {
            return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(savedCategory, WasteCategoryDTO.class));
        }
    }

    @GetMapping
    public ResponseEntity<List<WasteCategoryDTO>> getAllCategories() {
        List<WasteCategoryDTO> categories = categoryService.getAllCategories().stream()
                .map(category -> modelMapper.map(category, WasteCategoryDTO.class))
                .collect(Collectors.toList());
        if(categories.isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(categories);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteCategoryDTO> getCategoryById(@PathVariable Long id) {
        if(categoryService.getCategoryById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            return categoryService.getCategoryById(id)
                    .map(category -> ResponseEntity.ok(modelMapper.map(category, WasteCategoryDTO.class)))
                    .orElse(ResponseEntity.notFound().build());
        }
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<WasteItemDTO>> getItemsByCategory(@PathVariable Long id) {
        if (categoryService.getCategoryById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<WasteItemDTO> items = categoryService.getItemsByCategory(id).stream()
                    .map(item -> {
                        WasteItemDTO dto = modelMapper.map(item, WasteItemDTO.class);
                        dto.setCategoryId(item.getCategory().getId());
                        return dto;
                    })
                    .collect(Collectors.toList());
            if(items.isEmpty()) {
                return ResponseEntity.notFound().build();
            }else {
                return ResponseEntity.ok(items);
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (categoryService.getCategoryById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            if(categoryService.deleteCategory(id)) {
               return ResponseEntity.ok().build();
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<WasteCategoryDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody WasteCategoryDTO categoryDTO) {
        if (categoryService.getCategoryById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            WasteCategory category = modelMapper.map(categoryDTO, WasteCategory.class);
            category.setId(id);
            WasteCategory updatedCategory = categoryService.createCategory(category);
            if (updatedCategory == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }else {
                return ResponseEntity.ok(modelMapper.map(updatedCategory, WasteCategoryDTO.class));
            }
        }
    }
}
