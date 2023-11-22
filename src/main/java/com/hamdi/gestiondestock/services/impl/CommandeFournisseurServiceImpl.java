package com.hamdi.gestiondestock.services.impl;
import com.hamdi.gestiondestock.dto.CommandeFournisseurDto;
import com.hamdi.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.hamdi.gestiondestock.exception.EntityNotFoundException;
import com.hamdi.gestiondestock.exception.ErrorCodes;
import com.hamdi.gestiondestock.exception.InvalidEntityException;
import com.hamdi.gestiondestock.model.*;
import com.hamdi.gestiondestock.repository.*;
import com.hamdi.gestiondestock.services.CommandeFournisseurService;
import com.hamdi.gestiondestock.validator.CommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private CommandeFournisseurRepository commandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;
    private ArticleRepository articleRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;

    @Autowired
    public CommandeFournisseurServiceImpl(FournisseurRepository fournisseurRepository, ArticleRepository articleRepository,
                                     CommandeFournisseurRepository commandeFournisseurRepository,
                                     LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.ligneCommandeFournisseurRepository =ligneCommandeFournisseurRepository;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {

        List<String> errors = CommandeFournisseurValidator.validate(dto);
        if(!errors.isEmpty()) {
            log.error("commandeFournisseur not valid");
            throw new InvalidEntityException("commande fournisseur n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
        }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(dto.getFournisseur().getId());
        if(fournisseur.isEmpty()) {
            log.warn("fournisseur id not found", dto.getFournisseur().getId());
            throw new InvalidEntityException("aucun fournisseur avec l'd" +dto.getFournisseur().getId()+ "n'a ete touve dans la BD", ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }

        List<String> ArticleErrors = new ArrayList<>();

        if(dto.getLigneCommandeFournisseur()!= null) {
            dto.getLigneCommandeFournisseur().forEach(LCF -> {
                if(LCF.getArticle()!= null) {
                    Optional<Article> article = articleRepository.findById(LCF.getArticle().getId());
                    if(article.isEmpty()) {
                        ArticleErrors.add("L'article avec l'id" +LCF.getArticle().getId()+ "n'existe pas");
                    }
                } else {
                    ArticleErrors.add("impossible d'enregistrer une commande avec un article null");
                }
            });
        }

        if (!ArticleErrors.isEmpty()) {
            log.warn("article n'est pas valid");
            throw new InvalidEntityException("article n'est pas valid", ErrorCodes.ARTICLE_NOT_FOUND, ArticleErrors);
        }

        CommandeFournisseur savedCF = commandeFournisseurRepository.save(CommandeFournisseurDto.ToEntity(dto));

        if (dto.getLigneCommandeFournisseur() != null) {
            dto.getLigneCommandeFournisseur().forEach(LCF -> {
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.ToEntity(LCF);
                ligneCommandeFournisseur.setCommandefournisseur(savedCF);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
            });
        }
        return CommandeFournisseurDto.FromEntity(savedCF);

    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {

        if ( id==null) {
            log.error("commande fournisseur id is null");
            return null;
        }
        return commandeFournisseurRepository.findById(id).
                map(CommandeFournisseurDto::FromEntity).
                orElseThrow(() -> new EntityNotFoundException(
                        "aucun commande fournisseur avec l'id" +id+ "n'est trouve dans la BD", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
                ));
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {

        if ( !StringUtils.hasLength(code)) {
            log.error("commande fournisseur code is null");
            return null;
        }
        return commandeFournisseurRepository.findCommandeFournisseurByCode(code).
                map(CommandeFournisseurDto::FromEntity).
                orElseThrow(() -> new EntityNotFoundException(
                        "aucun commande fournisseur avec le code" +code+ "n'est trouve dans la BD", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {

        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::FromEntity).collect(Collectors.toList());

    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("commande client id is null");
            return;
        }
        commandeFournisseurRepository.deleteById(id);

    }
}
