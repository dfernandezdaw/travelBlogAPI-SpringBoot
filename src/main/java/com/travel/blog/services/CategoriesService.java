package com.travel.blog.services;

import com.travel.blog.payloads.CategoriesDTO;

import java.util.List;

public interface CategoriesService {
    CategoriesDTO createCategories(CategoriesDTO categoriesDTO);

    CategoriesDTO getCategoriesById(Long categoriesId);

    List<CategoriesDTO> getAllCategories();

    CategoriesDTO updateCategory(Long categoriesId, CategoriesDTO categoriesDTO);

    void deleteCategory(Long categoriesId);
}
