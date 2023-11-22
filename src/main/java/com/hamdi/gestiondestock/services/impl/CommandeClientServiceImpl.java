package com.hamdi.gestiondestock.services.impl;

import com.hamdi.gestiondestock.dto.CommandeClientDto;
import com.hamdi.gestiondestock.dto.LigneCommandeClientDto;
import com.hamdi.gestiondestock.exception.EntityNotFoundException;
import com.hamdi.gestiondestock.exception.ErrorCodes;
import com.hamdi.gestiondestock.exception.InvalidEntityException;
import com.hamdi.gestiondestock.model.Article;
import com.hamdi.gestiondestock.model.Client;
import com.hamdi.gestiondestock.model.CommandeClient;
import com.hamdi.gestiondestock.model.LigneCommandeClient;
import com.hamdi.gestiondestock.repository.ArticleRepository;
import com.hamdi.gestiondestock.repository.ClientRepository;
import com.hamdi.gestiondestock.repository.CommandeClientRepository;
import com.hamdi.gestiondestock.repository.LigneCommandeClientRepository;
import com.hamdi.gestiondestock.services.CommandeClientService;
import com.hamdi.gestiondestock.validator.CommandeClientValidator;
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
public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;

    @Autowired
    public CommandeClientServiceImpl(ClientRepository clientRepository, ArticleRepository articleRepository,
                                     CommandeClientRepository commandeClientRepository,
                                     LigneCommandeClientRepository ligneCommandeClientRepository) {
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.commandeClientRepository = commandeClientRepository;
        this.ligneCommandeClientRepository =ligneCommandeClientRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {

        List<String> errors = CommandeClientValidator.validate(dto);
        if(!errors.isEmpty()) {
            log.error("commandeClient not valid");
            throw new InvalidEntityException("commande client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
        }

        Optional<Client> client = clientRepository.findById(dto.getClient().getId());
        if(client.isEmpty()) {
            log.warn("client id not found", dto.getClient().getId());
            throw new InvalidEntityException("aucun client avec l'd" +dto.getClient().getId()+ "n'a ete touve dans la BD", ErrorCodes.CLIENT_NOT_FOUND);
        }

        List<String> ArticleErrors = new ArrayList<>();

        if(dto.getLigneCommandeClient()!= null) {
            dto.getLigneCommandeClient().forEach(LCC -> {
                if(LCC.getArticle()!= null) {
                    Optional<Article> article = articleRepository.findById(LCC.getArticle().getId());
                    if(article.isEmpty()) {
                        ArticleErrors.add("L'article avec l'id" +LCC.getArticle().getId()+ "n'existe pas");
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

        CommandeClient savedCC = commandeClientRepository.save(CommandeClientDto.ToEntity(dto));

        if (dto.getLigneCommandeClient() != null) {
            dto.getLigneCommandeClient().forEach(LCC -> {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.ToEntity(LCC);
                ligneCommandeClient.setCommandeclient(savedCC);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }
        return CommandeClientDto.FromEntity(savedCC);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
       if ( id==null) {
           log.error("commande client id is null");
           return null;
       }
      return commandeClientRepository.findById(id).
              map(CommandeClientDto::FromEntity).
              orElseThrow(() -> new EntityNotFoundException(
                      "aucun commande client avec l'id" +id+ "n'est trouve dans la BD", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
              ));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if ( !StringUtils.hasLength(code)) {
            log.error("commande client code is null");
            return null;
        }
        return commandeClientRepository.findCommandeClientByCode(code).
                map(CommandeClientDto::FromEntity).
                orElseThrow(() -> new EntityNotFoundException(
                        "aucun commande client avec le code" +code+ "n'est trouve dans la BD", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::FromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("commande client id is null");
            return;
        }
        commandeClientRepository.deleteById(id);

    }
}
