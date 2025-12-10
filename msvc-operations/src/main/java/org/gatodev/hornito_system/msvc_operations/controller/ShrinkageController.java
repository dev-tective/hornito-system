package org.gatodev.hornito_system.msvc_operations.controller;

import lombok.RequiredArgsConstructor;
import org.gatodev.hornito_system.msvc_operations.model.dto.request.ShrinkageRequestDto;
import org.gatodev.hornito_system.msvc_operations.model.entity.Shrinkage;
import org.gatodev.hornito_system.msvc_operations.service.ShrinkageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shrinkages")
@RequiredArgsConstructor
public class ShrinkageController {

    private final ShrinkageService shrinkageService;

    @GetMapping
    public List<Shrinkage> findAll() {
        return shrinkageService.findAll();
    }

    @GetMapping("/{id}")
    public Shrinkage findById(@PathVariable Long id) {
        return shrinkageService.findById(id);
    }

    @PostMapping
    public Shrinkage save(@RequestBody ShrinkageRequestDto request) {
        return shrinkageService.save(request.operation(), request.note());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        shrinkageService.deleteById(id);
    }
}
