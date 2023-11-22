package com.hamdi.gestiondestock.validator;
import com.hamdi.gestiondestock.dto.EntrepriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {

    public static List<String> validate(EntrepriseDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("veuillez renseigner le nom du l'entreprise ");
            errors.add("veuillez renseigner le description du l'entreprise ");
            errors.add("veuillez renseigner le code fiscal tel du l'entreprise ");
            errors.add("veuillez renseigner le mail du l'entreprise ");
            errors.add("veuillez renseigner le numtel du l'entreprise ");
            errors.add("veuillez renseigner le photo du l'entreprise ");
            return  errors;
        }

        if( !StringUtils.hasLength(dto.getName())) {
            errors.add("veuillez renseigner le nom du l'entreprise ");
        }
        if( !StringUtils.hasLength(dto.getDescription())) {
            errors.add("veuillez renseigner la description du l'entreprise ");
        }
        if( !StringUtils.hasLength(dto.getNumTel())) {
            errors.add("veuillez renseigner le num tel du l'entreprise ");
        }
        if( !StringUtils.hasLength(dto.getMail())) {
            errors.add("veuillez renseigner le mail du l'entreprise ");
        }
        if( !StringUtils.hasLength(dto.getCodeFiscal())) {
            errors.add("veuillez renseigner le code fiscal du l'entreprise ");
        }
        if( !StringUtils.hasLength(dto.getPhoto())) {
            errors.add("veuillez renseigner la photo du l'entreprise ");
        }

        return errors;

    }
}
