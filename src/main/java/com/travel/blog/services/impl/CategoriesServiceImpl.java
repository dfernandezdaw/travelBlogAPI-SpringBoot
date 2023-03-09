package com.travel.blog.services.impl;

import com.travel.blog.exceptions.ResourceNotFoundException;
import com.travel.blog.models.Categories;
import com.travel.blog.payloads.CategoriesDTO;
import com.travel.blog.repositories.CategoriesRepository;
import com.travel.blog.services.CategoriesService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    private final ModelMapper mapper;
    private final CategoriesRepository categoriesRepository;

    public CategoriesServiceImpl(ModelMapper mapper, CategoriesRepository categoriesRepository) {
        this.mapper = mapper;
        this.categoriesRepository = categoriesRepository;
    }
    @Override
    public CategoriesDTO createCategories(CategoriesDTO categoriesDTO) {
        Categories categories = convertToEntity(categoriesDTO);
        Categories savedCategories = categoriesRepository.save(categories);
        return convertToDTO(savedCategories);
    }

    @Override
    public CategoriesDTO getCategoriesById(Long categoriesId) {
        //Get category by id
        Categories categories = categoriesRepository.findById(categoriesId).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", categoriesId));
        return convertToDTO(categories);
    }

    @Override
    public List<CategoriesDTO> getAllCategories() {
        List<Categories> categories = categoriesRepository.findAll();
        return categories.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public CategoriesDTO updateCategories(Long categoriesId, CategoriesDTO categoriesDTO) {
        Categories categories = categoriesRepository.findById(categoriesId).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", categoriesId));

        categories.setName(categoriesDTO.getName()); //Update name

        Categories updatedCategories = categoriesRepository.save(categories);

        return convertToDTO(updatedCategories);
    }

    @Override
    public void deleteCategories(Long categoriesId) {

    }

    private CategoriesDTO convertToDTO(Categories category) {
        return mapper.map(category, CategoriesDTO.class);
    }

    private Categories convertToEntity(CategoriesDTO categoriesDTO) {
        return mapper.map(categoriesDTO, Categories.class);
    }
}
