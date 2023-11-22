package com.hamdi.gestiondestock.validator;

import com.hamdi.gestiondestock.dto.MvtStkDto;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MvtStkValidator {

    public static List<String> validate(MvtStkDto dto) {

        List<String> errors = new ArrayList<>();
        if (dto == null) {
            errors.add("veuillez renseigner le date du mvtSTK");
            errors.add("veuillez renseigner la quantite du mvtSTK");
            errors.add("veuillez renseigner l'article");
            errors.add("veuillez renseigner le type du mvtstk");

            return errors;
        }

        if (dto.getDteMvt() == null) {
            errors.add("veuillez renseigner le date du mvtSTK");
        }
        if (dto.getQuantite() == null || dto.getQuantite().compareTo(BigDecimal.ZERO) == 0) {
            errors.add("veuillez renseigner la quantite du mvtSTK");
        }
        if (dto.getArticle() == null || dto.getArticle().getId() == null) {
            errors.add("veuillez renseigner l'article");
        }
        if (!StringUtils.hasLength(dto.getTypemvtStk().name())) {
            errors.add("veuillez renseigner le type du mvtstk");
        }
        return errors;

    }

}

