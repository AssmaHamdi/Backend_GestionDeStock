package com.hamdi.gestiondestock.dto;

import com.hamdi.gestiondestock.model.LigneVente;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class LigneVenteDto {

    private Integer id;

    private VentesDto vente;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Integer idEntreprise;

    private ArticleDto article;



    public static LigneVenteDto FromEntity(LigneVente ligneVente) {

        if (ligneVente == null) {
            return null;
        }

        return   LigneVenteDto.builder()
                .id(ligneVente.getId())
                .quantite(ligneVente.getQuantite())
                .prixUnitaire(ligneVente.getPrixUnitaire())
                .idEntreprise(ligneVente.getIdEnreprise())
                .article(ArticleDto.FromEntity(ligneVente.getArticle()))
                .build();
    }

    public static LigneVente ToEntity(LigneVenteDto ligneVenteDto) {

        if (ligneVenteDto== null) {
            return  null;
        }

        LigneVente ligneVente = new LigneVente();

        ligneVente.setId(ligneVenteDto.getId());
        ligneVente.setQuantite(ligneVenteDto.getQuantite());
        ligneVente.setPrixUnitaire(ligneVenteDto.getPrixUnitaire());
        ligneVente.setIdEnreprise(ligneVente.getIdEnreprise());

        return ligneVente;
    }



}
