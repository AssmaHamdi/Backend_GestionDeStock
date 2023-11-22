package com.hamdi.gestiondestock.validator;

import com.hamdi.gestiondestock.dto.CommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator {

    public static List<String> validate(CommandeClientDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("veuillez renseigner le code du CommandeClient ");
            errors.add("veuillez renseigner la date de la commandeClient ");
            errors.add("veuillez renseigner le client ");
            return errors;
        }

        if (!StringUtils.hasLength(dto.getCode())) {
            errors.add("veuillez renseigner le code du commandeClient ");
        }
        if (dto.getDateCommande()==null) {
            errors.add("veuillez renseigner la date de la commandeClient ");
        }
        if (dto.getClient()==null || dto.getClient().getId()==null) {
            errors.add("veuillez renseigner le client ");
        }
        return errors;
    }
}
