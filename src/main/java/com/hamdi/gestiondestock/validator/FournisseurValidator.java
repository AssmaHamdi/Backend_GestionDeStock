package com.hamdi.gestiondestock.validator;

import com.hamdi.gestiondestock.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {

    public static List<String> validate(FournisseurDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("veuillez renseigner le nom du fournisseur ");
            errors.add("veuillez renseigner le prenom du fournisseur ");
            errors.add("veuillez renseigner le num tel du fournisseur ");
            errors.add("veuillez renseigner le mail du fournisseur ");
            return  errors;
        }

        if( !StringUtils.hasLength(dto.getNom())) {
            errors.add("veuillez renseigner le nom du fournisseur ");
        }
        if( !StringUtils.hasLength(dto.getPrenom())) {
            errors.add("veuillez renseigner le prenom du fournisseur ");
        }
        if( !StringUtils.hasLength(dto.getNumTel())) {
            errors.add("veuillez renseigner le num tel du fournisseur ");
        }
        if( !StringUtils.hasLength(dto.getMail())) {
            errors.add("veuillez renseigner le mail du fournisseur ");
        }

        return errors;
} }
