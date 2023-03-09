package com.travel.blog.controllers;

import com.travel.blog.payloads.CategoriesDTO;
import com.travel.blog.services.CategoriesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CategoriesController {
    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoriesDTO> createCategories(@RequestBody CategoriesDTO categoriesDTO) {
        return new ResponseEntity<>(categoriesService.createCategories(categoriesDTO), HttpStatus.CREATED);
    }

    @GetMapping("/categories/{categoriesId}")
    public ResponseEntity<CategoriesDTO> getCategoriesById(@PathVariable Long categoriesId) {
        CategoriesDTO categoriesDTO = categoriesService.getCategoriesById(categoriesId);
        return new ResponseEntity<>(categoriesDTO, HttpStatus.OK);
    }

}
