package com.hamdi.gestiondestock.services.impl;

import com.hamdi.gestiondestock.dto.CategoryDto;
import com.hamdi.gestiondestock.exception.EntityNotFoundException;
import com.hamdi.gestiondestock.exception.ErrorCodes;
import com.hamdi.gestiondestock.exception.InvalidEntityException;
import com.hamdi.gestiondestock.repository.CategoryRepository;
import com.hamdi.gestiondestock.services.CategoryService;
import com.hamdi.gestiondestock.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {

        List<String> errors = CategoryValidator.validate(dto);
        if(!errors.isEmpty()) {
            log.error("Category is not valid", dto);
            throw new InvalidEntityException("Category n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }
        return CategoryDto.FromEntity(categoryRepository.save(CategoryDto.ToEntity(dto)));
    }

    @Override
    public CategoryDto findById(Integer id) {

        if (id== null) {
            log.error("Category ID is null");
            return null;
        }
        return categoryRepository.findById(id)
                .map(CategoryDto::FromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "category avec l'id =" +id+ "n'est trouve dans la BD", ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public CategoryDto findByCode(String code) {

        if (code== null) {
            log.error("Category code is null");
            return null;
        }
        return categoryRepository.findCategoryByCode(code)
                .map(CategoryDto::FromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "category avec le code =" +code+ "n'est trouve dans la BD", ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public List<CategoryDto> findAll() {

        return categoryRepository.findAll().stream()
                .map(CategoryDto::FromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null) {
            log.error("category id is null");
            return;
        }
        categoryRepository.deleteById(id);
    }
}
