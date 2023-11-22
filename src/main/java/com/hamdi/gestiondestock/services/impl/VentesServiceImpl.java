package com.hamdi.gestiondestock.services.impl;
import com.hamdi.gestiondestock.dto.CommandeClientDto;
import com.hamdi.gestiondestock.dto.LigneVenteDto;
import com.hamdi.gestiondestock.dto.VentesDto;
import com.hamdi.gestiondestock.exception.EntityNotFoundException;
import com.hamdi.gestiondestock.exception.ErrorCodes;
import com.hamdi.gestiondestock.exception.InvalidEntityException;
import com.hamdi.gestiondestock.model.Article;
import com.hamdi.gestiondestock.model.LigneVente;
import com.hamdi.gestiondestock.model.Ventes;
import com.hamdi.gestiondestock.repository.ArticleRepository;
import com.hamdi.gestiondestock.repository.LigneVenteRepository;
import com.hamdi.gestiondestock.repository.VentesRepository;
import com.hamdi.gestiondestock.services.VentesService;
import com.hamdi.gestiondestock.validator.VentesValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VentesServiceImpl implements VentesService {

    private ArticleRepository articleRepository;
    private VentesRepository ventesRepository;
    private LigneVenteRepository ligneVenteRepository;

    @Autowired
    public VentesServiceImpl(ArticleRepository articleRepository, VentesRepository ventesRepository,
                             LigneVenteRepository ligneVenteRepository) {
        this.articleRepository = articleRepository;
        this.ventesRepository = ventesRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public VentesDto save(VentesDto dto) {

        List<String> errors = VentesValidator.validate(dto);
        if(!errors.isEmpty()) {
            log.error("ventes not valid");
            throw new InvalidEntityException("ventes n'est pas valide", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        List<String> ArticleErrors = new ArrayList<>();
            dto.getLigneVentes().forEach( LigneVenteDto -> {
                Optional<Article> article = articleRepository.findById(LigneVenteDto.getArticle().getId());
                if (article.isEmpty()) {
                    ArticleErrors.add("L'article avec l'id" + LigneVenteDto.getArticle().getId() + "n'existe pas");
                }
            });

        if (!ArticleErrors.isEmpty()) {
            log.warn("article n'est pas valid");
            throw new InvalidEntityException("article n'est pas valid", ErrorCodes.VENTE_NOT_VALID, ArticleErrors);
        }
        Ventes savedVentes = ventesRepository.save(VentesDto.ToEntity(dto));
        dto.getLigneVentes().forEach(LigneVentesDto -> {
            LigneVente ligneVentes = LigneVenteDto.ToEntity(LigneVentesDto);
            ligneVentes.setVente(savedVentes);
            ligneVenteRepository.save(ligneVentes);
        });

        return VentesDto.FromEntity(savedVentes);

    }

    @Override
    public VentesDto findById(Integer id) {
        if ( id==null) {
            log.error("vente id is null");
            return null;
        }
        return ventesRepository.findById(id).
                map(VentesDto::FromEntity).
                orElseThrow(() -> new EntityNotFoundException(
                        "aucun vente avec l'id" +id+ "n'est trouve dans la BD", ErrorCodes.VENTE_NOT_FOUND
                ));
    }

    @Override
    public VentesDto findByCode(String code) {
        if ( code==null) {
            log.error("vente code is null");
            return null;
        }
        return ventesRepository.findVentesByCode(code).
                map(VentesDto::FromEntity).
                orElseThrow(() -> new EntityNotFoundException(
                        "aucun vente avec le code" +code+ "n'est trouve dans la BD", ErrorCodes.VENTE_NOT_FOUND
                ));
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll().stream()
                .map(VentesDto::FromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("commande client id is null");
            return;
        }
        ventesRepository.deleteById(id);


    }
}
