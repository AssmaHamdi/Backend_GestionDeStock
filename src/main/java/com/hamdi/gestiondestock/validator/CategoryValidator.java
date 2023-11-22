package com.hamdi.gestiondestock.validator;

import com.hamdi.gestiondestock.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {

    public static List<String> validate(CategoryDto categorydto) {
        List<String> errors = new ArrayList<>();

        if(categorydto == null || !StringUtils.hasLength(categorydto.getCode())) {
            errors.add("veuillez renseigner le code de la category ");
        }
        return errors;

    }
}
