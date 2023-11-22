package com.hamdi.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hamdi.gestiondestock.model.CommandeFournisseur;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Builder
@Data

public class CommandeFournisseurDto {

    private Integer id;

    private String code;

    private Instant dateCommande;

    private FournisseurDto fournisseur;

    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseur;

    private Integer idEntreprise;


    public static CommandeFournisseurDto FromEntity(CommandeFournisseur commandeFournisseur) {

        if (commandeFournisseur == null) {
            return null;
        }
         return CommandeFournisseurDto.builder()
                 .id(commandeFournisseur.getId())
                 .code(commandeFournisseur.getCode())
                 .dateCommande(commandeFournisseur.getDateCommande())
                 .fournisseur(FournisseurDto.FromEntity(commandeFournisseur.getFournisseur()))
                 .idEntreprise(commandeFournisseur.getIdEnreprise())
                 .build();
    }

    public static CommandeFournisseur ToEntity(CommandeFournisseurDto commandeFournisseurDto) {
         if (commandeFournisseurDto == null) {
             return null;
         }

         CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
         commandeFournisseur.setId(commandeFournisseurDto.getId());
         commandeFournisseur.setCode(commandeFournisseurDto.getCode());
         commandeFournisseur.setDateCommande(commandeFournisseurDto.getDateCommande());
         commandeFournisseur.setIdEnreprise(commandeFournisseurDto.getIdEntreprise());

         return commandeFournisseur;
    }
}
