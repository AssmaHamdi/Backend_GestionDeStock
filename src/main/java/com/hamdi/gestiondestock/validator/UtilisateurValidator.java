package com.hamdi.gestiondestock.validator;

import com.hamdi.gestiondestock.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {

    public static List<String> validate(UtilisateurDto utilisateurDto) {
        List<String> errors = new ArrayList<>();

        if (utilisateurDto == null) {
            errors.add("veuillez renseigner le nom d'utilisateur  ");
            errors.add("veuillez renseigner le prenom d'utilisateur ");
            errors.add("veuillez renseigner le mail d'utilisateur ");
            errors.add("veuillez renseigner le mot de passe d'utilisateur ");
            errors.add("veuillez renseigner la date de naissance d'utilisateur ");
            errors.add("veuillez renseigner l'Adresse d'utilisateur ");
            return errors;
        }

        if(!StringUtils.hasLength(utilisateurDto.getNom())) {
            errors.add("veuillez renseigner le nom d'utilisateur  ");
        }
        if( !StringUtils.hasLength(utilisateurDto.getPrenom())) {
            errors.add("veuillez renseigner le prenom d'utilisateur ");
        }
        if( !StringUtils.hasLength(utilisateurDto.getMail())) {
            errors.add("veuillez renseigner le mail d'utilisateur ");
        }
        if( !StringUtils.hasLength(utilisateurDto.getMotDePasse())) {
            errors.add("veuillez renseigner le mot de passe d'utilisateur ");
        }
        if(utilisateurDto.getDateDeNaissance() ==null) {
            errors.add("veuillez renseigner la date de naissance d'utilisateur ");
        }
        if(utilisateurDto.getAdresse() == null) {
            errors.add("veuillez renseigner l'Adresse d'utilisateur ");
        } else {
            if( !StringUtils.hasLength(utilisateurDto.getAdresse().getAdresse1())) {
                errors.add("le champs Adresse 1 est obligatoir ");
            }
            if( !StringUtils.hasLength(utilisateurDto.getAdresse().getCodePostale())) {
                errors.add("le champs code postale est obligatoir ");
            } if( !StringUtils.hasLength(utilisateurDto.getAdresse().getPays())) {
                errors.add("le champs pays est obligatoir ");
            }
            if( !StringUtils.hasLength(utilisateurDto.getAdresse().getVille())) {
                errors.add("le champs ville est obligatoir  ");
            }
        }

        return errors;

    }
}
