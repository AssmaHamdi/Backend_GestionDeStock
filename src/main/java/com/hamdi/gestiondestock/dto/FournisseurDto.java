package com.hamdi.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hamdi.gestiondestock.model.Entreprise;
import com.hamdi.gestiondestock.model.Fournisseur;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data

public class FournisseurDto {

    private Integer id;

    private String nom;

    private String prenom;

    @JsonIgnore
    private AdresseDto adresse;

    private String photo;

    private String mail;

    private String numTel;

    @JsonIgnore
    private List<CommandeFournisseurDto> commandeFournisseurs;

    private Integer idEntreprise;


    public static FournisseurDto FromEntity(Fournisseur fournisseur) {

        if (fournisseur == null) {
            return null;
        }

        return FournisseurDto.builder()
                .id(fournisseur.getId())
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .photo(fournisseur.getPhoto())
                .mail(fournisseur.getMail())
                .numTel(fournisseur.getNumTel())
                .idEntreprise(fournisseur.getIdEnreprise())
                .build();
    }

    public static Fournisseur ToEntity(FournisseurDto fournisseurDto) {

        if (fournisseurDto == null) {
            return null;
        }

        Fournisseur fournisseur = new Fournisseur();

        fournisseur.setId(fournisseurDto.getId());
        fournisseur.setNom(fournisseurDto.getNom());
        fournisseur.setPrenom(fournisseurDto.getPrenom());
        fournisseur.setPhoto(fournisseurDto.getPhoto());
        fournisseur.setMail(fournisseurDto.getMail());
        fournisseur.setNumTel(fournisseurDto.getNumTel());
        fournisseur.setIdEnreprise(fournisseurDto.getIdEntreprise());

        return fournisseur;
    }




}
