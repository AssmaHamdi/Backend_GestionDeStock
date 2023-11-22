package com.hamdi.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hamdi.gestiondestock.model.LigneCommandeClient;
import com.hamdi.gestiondestock.model.LigneCommandeFournisseur;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class LigneCommandeFournisseurDto {

    private Integer id;

    private ArticleDto article;

    @JsonIgnore
    private CommandeFournisseurDto commandefournisseur;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Integer idEntreprise;


    public static LigneCommandeFournisseurDto FromEntity(LigneCommandeFournisseur ligneCommandeFournisseur) {

        if (ligneCommandeFournisseur == null) {
            return null;
        }

        return   LigneCommandeFournisseurDto.builder()
                .id(ligneCommandeFournisseur.getId())
                .quantite(ligneCommandeFournisseur.getQuantite())
                .prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
                .article(ArticleDto.FromEntity(ligneCommandeFournisseur.getArticle()))
                .idEntreprise(ligneCommandeFournisseur.getIdEnreprise())
                .build();
    }

    public static LigneCommandeFournisseur ToEntity(LigneCommandeFournisseurDto ligneCommandeFournisseurDto) {

        if (ligneCommandeFournisseurDto == null) {
            return  null;
        }

        LigneCommandeFournisseur ligneCommandeFournisseur = new LigneCommandeFournisseur();

        ligneCommandeFournisseur.setId(ligneCommandeFournisseurDto.getId());
        ligneCommandeFournisseur.setQuantite(ligneCommandeFournisseurDto.getQuantite());
        ligneCommandeFournisseur.setPrixUnitaire(ligneCommandeFournisseurDto.getPrixUnitaire());
        ligneCommandeFournisseur.setIdEnreprise(ligneCommandeFournisseurDto.getIdEntreprise());

        return ligneCommandeFournisseur;
    }



}
