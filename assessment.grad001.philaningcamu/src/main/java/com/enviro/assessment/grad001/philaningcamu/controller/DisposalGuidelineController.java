package com.enviro.assessment.grad001.philaningcamu.controller;

import com.enviro.assessment.grad001.philaningcamu.model.DTO.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.philaningcamu.model.entity.DisposalGuideline;
import com.enviro.assessment.grad001.philaningcamu.model.entity.WasteCategory;
import com.enviro.assessment.grad001.philaningcamu.service.DisposalGuidelineService;
import com.enviro.assessment.grad001.philaningcamu.service.WasteCategoryService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/guidelines")
public class DisposalGuidelineController {
    private final DisposalGuidelineService guidelineService;
    private final ModelMapper modelMapper;
    private final WasteCategoryService wasteCategoryService;

    @Autowired
    public DisposalGuidelineController(DisposalGuidelineService guidelineService, ModelMapper modelMapper, WasteCategoryService wasteCategoryService) {
        this.guidelineService = guidelineService;
        this.modelMapper = modelMapper;
        this.wasteCategoryService = wasteCategoryService;
    }

    @PostMapping
    public ResponseEntity<DisposalGuidelineDTO> createGuideline(
            @Parameter(description = "Disposal guideline details", required = true)
            @Valid @RequestBody DisposalGuidelineDTO guideline) {

        if (wasteCategoryService.getCategoryById(guideline.getCategoryId()).isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            WasteCategory category = new WasteCategory();
            category.setId(guideline.getCategoryId());
            DisposalGuideline guidelineEntity = modelMapper.map(guideline, DisposalGuideline.class);
            guidelineEntity.setCategory(category);
            DisposalGuideline created = guidelineService.createGuideline(guidelineEntity);
            DisposalGuidelineDTO dto = modelMapper.map(created, DisposalGuidelineDTO.class);
            dto.setCategoryId(created.getCategory().getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisposalGuidelineDTO> updateGuideline(
            @Parameter(description = "Guideline ID", required = true) @PathVariable Long id,
            @Parameter(description = "Updated guideline details", required = true)
            @Valid @RequestBody DisposalGuidelineDTO guideline) {
        return guidelineService.getGuidelineById(id)
                .map(existing -> {
                    guideline.setId(id);
                    WasteCategory category = new WasteCategory();
                    category.setId(guideline.getCategoryId());
                    DisposalGuideline disposalGuidelineEntity = modelMapper.map(guideline, DisposalGuideline.class);
                    disposalGuidelineEntity.setCategory(category);
                    DisposalGuideline updated = guidelineService.updateGuideline(disposalGuidelineEntity);
                    DisposalGuidelineDTO dto = modelMapper.map(updated, DisposalGuidelineDTO.class);
                    dto.setCategoryId(updated.getCategory().getId());
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<DisposalGuidelineDTO>> getGuidelinesByCategory(
            @Parameter(description = "Category ID", required = true) @PathVariable Long categoryId) {
        List<DisposalGuideline> guidelines = guidelineService.getGuidelinesByCategory(categoryId);
        List<DisposalGuidelineDTO> dto = guidelines.stream()
                .map(guideline -> {
                    DisposalGuidelineDTO item = modelMapper.map(guideline, DisposalGuidelineDTO.class);
                    item.setCategoryId(guideline.getCategory().getId());
                    return item;
                })
                .toList();
        return ResponseEntity.ok(dto);
    }


    @GetMapping
    public ResponseEntity<List<DisposalGuidelineDTO>> getAllGuidelines() {
        List<DisposalGuideline> guidelines = guidelineService.getAllGuidelines();
        List<DisposalGuidelineDTO> dto = guidelines.stream()
                .map(guideline -> {
                    DisposalGuidelineDTO item = modelMapper.map(guideline, DisposalGuidelineDTO.class);
                    item.setCategoryId(guideline.getCategory().getId());
                    return item;
                })
                .toList();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisposalGuidelineDTO> getGuidelineById(
            @Parameter(description = "Guideline ID", required = true) @PathVariable Long id) {
        return guidelineService.getGuidelineById(id)
                .map(guideline -> {
                    DisposalGuidelineDTO dto = modelMapper.map(guideline, DisposalGuidelineDTO.class);
                    dto.setCategoryId(guideline.getCategory().getId());
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuideline(
            @Parameter(description = "Guideline ID", required = true) @PathVariable Long id) {
        return guidelineService.getGuidelineById(id)
                .map(guideline -> {
                    guidelineService.deleteGuideline(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}