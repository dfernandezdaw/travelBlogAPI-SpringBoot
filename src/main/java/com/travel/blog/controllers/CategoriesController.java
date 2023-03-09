package com.travel.blog.controllers;

import com.travel.blog.payloads.CategoriesDTO;
import com.travel.blog.services.CategoriesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CategoriesController {
    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    //Create category
    @PostMapping("/categories")
    public ResponseEntity<CategoriesDTO> createCategories(@RequestBody CategoriesDTO categoriesDTO) {
        return new ResponseEntity<>(categoriesService.createCategories(categoriesDTO), HttpStatus.CREATED);
    }

    //Get category by id
    @GetMapping("/categories/{categoriesId}")
    public ResponseEntity<CategoriesDTO> getCategoriesById(@PathVariable Long categoriesId) {
        CategoriesDTO categoriesDTO = categoriesService.getCategoriesById(categoriesId);
        return new ResponseEntity<>(categoriesDTO, HttpStatus.OK);
    }

    //Get all categories
    @GetMapping("/categories")
    public List<CategoriesDTO> getAllCategories() {
        return categoriesService.getAllCategories();
    }

    //Update category by id
    @PutMapping("/categories/{categoriesId}")
    public ResponseEntity<CategoriesDTO> updateCategory(@PathVariable Long categoriesId,
                                                          @RequestBody CategoriesDTO categoriesDTO) {
        CategoriesDTO updatedCategories = categoriesService.updateCategory(categoriesId, categoriesDTO);
        return new ResponseEntity<>(updatedCategories, HttpStatus.OK);
    }

    //Delete category by id
    @DeleteMapping("/categories/{categoriesId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoriesId) {
        categoriesService.deleteCategory(categoriesId);
        return new ResponseEntity<>("Category with id " + categoriesId + " was deleted", HttpStatus.OK);
    }

}
