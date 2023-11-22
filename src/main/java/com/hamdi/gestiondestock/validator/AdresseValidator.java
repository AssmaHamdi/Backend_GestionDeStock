package com.hamdi.gestiondestock.validator;
import com.hamdi.gestiondestock.dto.AdresseDto;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class AdresseValidator {

    public static List<String> validate(AdresseDto adressedto) {
        List<String> errors = new ArrayList<>();

        if (adressedto == null) {
            errors.add("veuillez renseigner l'adresse 1 ");
            errors.add("veuillez renseigner la ville ");
            errors.add("veuillez renseigner le pays ");
            errors.add("veuillez renseigner le code postale ");
            return errors;
        }

        if (!StringUtils.hasLength(adressedto.getAdresse1())) {
            errors.add("veuillez renseigner l'adresse 1 ");
        }
        if (!StringUtils.hasLength(adressedto.getVille())) {
            errors.add("veuillez renseigner la ville ");
        }
        if (!StringUtils.hasLength(adressedto.getPays())) {
            errors.add("veuillez renseigner le pays ");
        }
        if (!StringUtils.hasLength(adressedto.getCodePostale())) {
            errors.add("veuillez renseigner le code postale ");
        }

        return errors;
    }

    }
