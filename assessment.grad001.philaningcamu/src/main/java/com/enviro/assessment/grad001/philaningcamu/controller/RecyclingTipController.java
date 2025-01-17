package com.enviro.assessment.grad001.philaningcamu.controller;

import com.enviro.assessment.grad001.philaningcamu.model.DTO.RecyclingTipDTO;
import com.enviro.assessment.grad001.philaningcamu.model.entity.RecyclingTip;
import com.enviro.assessment.grad001.philaningcamu.model.entity.WasteItem;
import com.enviro.assessment.grad001.philaningcamu.service.RecyclingTipService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tips")
public class RecyclingTipController {
    private final RecyclingTipService tipService;
    private final ModelMapper modelMapper;

    // Which means that the RecyclingTipService and ModelMapper objects are injected into the RecyclingTipController class at runtime.
    @Autowired
    public RecyclingTipController(RecyclingTipService tipService, ModelMapper modelMapper) {
        this.tipService = tipService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<RecyclingTipDTO> createTip(
            @Valid @RequestBody RecyclingTipDTO tipDTO) {
        if (!tipService.getWasteItem(tipDTO.getWasteItemId())) {
            return ResponseEntity.notFound().build();
        } else {
            RecyclingTip tip = modelMapper.map(tipDTO, RecyclingTip.class);
            WasteItem wasteItem = new WasteItem();
            wasteItem.setId(tipDTO.getWasteItemId());
            tip.setWasteItem(wasteItem);
            RecyclingTipDTO tipResponse = modelMapper.map(tipService.createTip(tip), RecyclingTipDTO.class);
            tipResponse.setWasteItemId(tip.getWasteItem().getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(tipResponse);
        }
    }

    @GetMapping("/category/{categoryId}")
    public List<RecyclingTipDTO> getPublishedTipsByCategory(@PathVariable Long categoryId) {
        return tipService.getPublishedTipsByCategory(categoryId).stream()
                .map(tip -> {
                    RecyclingTipDTO tipDTO = modelMapper.map(tip, RecyclingTipDTO.class);
                    tipDTO.setWasteItemId(tip.getWasteItem().getId());
                    return  tipDTO;
                })
                .collect(Collectors.toList());
    }


    @GetMapping
    public ResponseEntity<List<RecyclingTipDTO>> getAllTips() {
        List<RecyclingTipDTO> tips = tipService.getAllTips().stream()
                .map(tip -> {
                    RecyclingTipDTO tipDTO = modelMapper.map(tip, RecyclingTipDTO.class);
                    tipDTO.setWasteItemId(tip.getWasteItem().getId());
                    return tipDTO;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(tips);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTip(@PathVariable Long id) {
        if (!tipService.getWasteItem(id)) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("deleted", false));
        }else {
            tipService.deleteTip(id);
            return ResponseEntity.ok(Map.of("deleted", true));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecyclingTipDTO> updateTip(
            @PathVariable Long id,
            @Valid @RequestBody RecyclingTipDTO tipDTO) {
        if (tipService.getTipById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            RecyclingTip tip = modelMapper.map(tipDTO, RecyclingTip.class);
            WasteItem wasteItem = new WasteItem();
            wasteItem.setId(tipDTO.getWasteItemId());
            tip.setWasteItem(wasteItem);
            tip.setId(id);
            RecyclingTipDTO tipResponse = modelMapper.map(tipService.createTip(tip), RecyclingTipDTO.class);
            tipResponse.setWasteItemId(tip.getWasteItem().getId());
            return ResponseEntity.ok(tipResponse);
        }
    }
}
