package com.hamdi.gestiondestock.validator;

import com.hamdi.gestiondestock.dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {

    public static List<String> validate(ArticleDto dto) {
        List<String> errors = new ArrayList<>();

        if ( dto == null) {
            errors.add("veuillez renseigner le code de l'article ");
            errors.add("veuillez renseigner la designation de l'article ");
            errors.add("veuillez renseigner le prix unitaire ht de l'article ");
            errors.add("veuillez renseigner le taux tva de l'article ");
            errors.add("veuillez renseigner le prix unitaire ttc de l'article ");
            errors.add("veuillez renseigner la category de l'article ");
            return errors;
        }

        if(!StringUtils.hasLength(dto.getCodeArticle())) {
            errors.add("veuillez renseigner le code de l'article ");
        }
        if( !StringUtils.hasLength(dto.getDesignation())) {
            errors.add("veuillez renseigner la designation de l'article ");
        }
        if( dto.getPrixUnitaireHt() == null) {
            errors.add("veuillez renseigner le prix unitaire ht de l'article ");
        }
        if( dto.getTauxTva() == null) {
            errors.add("veuillez renseigner le taux tva de l'article ");
        }
        if((dto.getPrixUnitaireTtc() == null)) {
            errors.add("veuillez renseigner le prix unitaire ttc de l'article ");
        }
        if( dto.getCategory() == null) {
            errors.add("veuillez renseigner la category de l'article ");
        }
        return errors;

    }
}
