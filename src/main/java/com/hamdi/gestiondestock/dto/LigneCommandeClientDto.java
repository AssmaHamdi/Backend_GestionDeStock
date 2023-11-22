package com.hamdi.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hamdi.gestiondestock.model.LigneCommandeClient;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data

public class LigneCommandeClientDto {

    private Integer id;

    private ArticleDto article;

    @JsonIgnore
    private CommandeClientDto commandeclient;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Integer idEntreprise;


    public static LigneCommandeClientDto FromEntity(LigneCommandeClient ligneCommandeClient) {

        if (ligneCommandeClient == null) {
            return null;
        }

        return LigneCommandeClientDto.builder()
                .id(ligneCommandeClient.getId())
                .quantite(ligneCommandeClient.getQuantite())
                .prixUnitaire(ligneCommandeClient.getPrixUnitaire())
                .article(ArticleDto.FromEntity(ligneCommandeClient.getArticle()))
                .idEntreprise(ligneCommandeClient.getIdEnreprise())
                .build();
    }

    public static LigneCommandeClient ToEntity(LigneCommandeClientDto ligneCommandeClientDto) {

        if (ligneCommandeClientDto == null) {
            return  null;
        }

        LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();

        ligneCommandeClient.setId(ligneCommandeClientDto.getId());
        ligneCommandeClient.setQuantite(ligneCommandeClientDto.getQuantite());
        ligneCommandeClient.setPrixUnitaire(ligneCommandeClientDto.getPrixUnitaire());
        ligneCommandeClient.setIdEnreprise(ligneCommandeClientDto.getIdEntreprise());

        return ligneCommandeClient;
    }

}
