package com.hamdi.gestiondestock.services.impl;

import com.hamdi.gestiondestock.dto.UtilisateurDto;
import com.hamdi.gestiondestock.exception.EntityNotFoundException;
import com.hamdi.gestiondestock.exception.ErrorCodes;
import com.hamdi.gestiondestock.exception.InvalidEntityException;
import com.hamdi.gestiondestock.repository.UtilisateurRepository;
import com.hamdi.gestiondestock.services.UtilisateurService;
import com.hamdi.gestiondestock.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {

        List<String> errors = UtilisateurValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("utilisateur is not valid", dto);
            throw new InvalidEntityException("utlilisateur n'est pas valid", ErrorCodes.UTILISATEUR_NOT_VALID, errors );
        }
        return UtilisateurDto.FromEntity(utilisateurRepository.save(UtilisateurDto.ToEntity(dto)));
    }
    @Override
    public UtilisateurDto findById(Integer id) {
        if (id ==null) {
            log.error("utilisateur id is null");
            return null;
        }
        return utilisateurRepository.findById(id).map(UtilisateurDto::FromEntity)
                .orElseThrow(() -> new EntityNotFoundException("utilisateur avec l'id" +id+ "nest pas touve,",
                        ErrorCodes.UTILISATEUR_NOT_FOUND));
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::FromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("utilisateur id is null");
            return;
        }
        utilisateurRepository.deleteById(id);
    }
}
