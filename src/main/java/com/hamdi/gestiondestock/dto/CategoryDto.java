package com.hamdi.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hamdi.gestiondestock.model.Category;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CategoryDto {

    private Integer id;

    private String code;

    private String designation;

    @JsonIgnore
    private List<ArticleDto> aricles;

    private Integer idEntreprise;


    public static CategoryDto FromEntity(Category category) {

        if (category == null) {
            return null;
            //TODO throw an exception
        }

        return CategoryDto.builder()
                .id(category.getId())
                .code(category.getCode())
                .designation(category.getDesignation())
                .idEntreprise(category.getIdEnreprise())
                .build();
    }

    public static Category ToEntity(CategoryDto categoryDto){

        if (categoryDto == null) {
            return null;
            // TODO throw an exception
        }

        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setCode(categoryDto.getCode());
        category.setDesignation(categoryDto.getDesignation());
        category.setIdEnreprise(categoryDto.getIdEntreprise());

        return category;
    }


}