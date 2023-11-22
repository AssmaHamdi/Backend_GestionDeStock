package com.hamdi.gestiondestock.services.impl;

import com.hamdi.gestiondestock.dto.FournisseurDto;
import com.hamdi.gestiondestock.exception.EntityNotFoundException;
import com.hamdi.gestiondestock.exception.ErrorCodes;
import com.hamdi.gestiondestock.exception.InvalidEntityException;
import com.hamdi.gestiondestock.repository.FournisseurRepository;
import com.hamdi.gestiondestock.services.FournisseurService;
import com.hamdi.gestiondestock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

    private FournisseurRepository fournisseurRepository;

    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto dto) {

        List<String> errors = FournisseurValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("fournisseur is not valid", dto);
            throw new InvalidEntityException("fournisseur n'est pas valid", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
        }
        return FournisseurDto.FromEntity(fournisseurRepository.save(FournisseurDto.ToEntity(dto)));
    }

    @Override
    public FournisseurDto findById(Integer id) {
        if (id == null) {
            log.error("fournisseur id is null");
            return null;
        }
            return fournisseurRepository.findById(id).map(FournisseurDto::FromEntity).
                    orElseThrow(() -> new EntityNotFoundException(
                            "fournisseur avec l'id" + id + "n'est pas trouve", ErrorCodes.FOURNISSEUR_NOT_FOUND
                    ));
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::FromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("fournisseur id is null");
            return;
        }
            fournisseurRepository.deleteById(id);
        }

    }

