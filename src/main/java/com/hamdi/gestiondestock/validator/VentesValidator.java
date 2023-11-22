package com.hamdi.gestiondestock.validator;
import com.hamdi.gestiondestock.dto.VentesDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class VentesValidator {

    public static List<String> validate(VentesDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("veuillez renseigner le code du ventes ");
            errors.add("veuillez renseigner la date de la commande ");
            return errors;
        }

        if (!StringUtils.hasLength(dto.getCode())) {
            errors.add("veuillez renseigner le code du ventes ");
        }
        if (dto.getDatevente()==null) {
            errors.add("veuillez renseigner la date de la commande ");
        }


        return errors;
    }
}
