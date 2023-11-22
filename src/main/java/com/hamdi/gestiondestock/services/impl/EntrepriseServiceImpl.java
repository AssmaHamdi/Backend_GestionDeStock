package com.hamdi.gestiondestock.services.impl;

import com.hamdi.gestiondestock.dto.EntrepriseDto;
import com.hamdi.gestiondestock.exception.EntityNotFoundException;
import com.hamdi.gestiondestock.exception.ErrorCodes;
import com.hamdi.gestiondestock.exception.InvalidEntityException;
import com.hamdi.gestiondestock.repository.EntrepriseRepository;
import com.hamdi.gestiondestock.services.EntrepriseService;
import com.hamdi.gestiondestock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {

        List<String> errors = EntrepriseValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Entreprise is not valid");
            throw new InvalidEntityException("entreprise n'est pas valid", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }
        return EntrepriseDto.FromEntity(entrepriseRepository.save(EntrepriseDto.ToEntity(dto)));
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        if (id==null) {
            log.error("Entreprise id is null");
            return null;
        }
        return entrepriseRepository.findById(id).
                map(EntrepriseDto::FromEntity).
                orElseThrow(() -> new EntityNotFoundException(
                        "entreprise avec l'id" +id+ "n'est pas trouve dans la BD" , ErrorCodes.ENTREPRISE_NOT_FOUND
                ));
}

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::FromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id==null) {
            log.error("entreprise id is null");
            return;
        }
        entrepriseRepository.deleteById(id);

    }
}
