package com.hamdi.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hamdi.gestiondestock.model.Entreprise;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EntrepriseDto {

    private Integer id;

    private String name;

    private String description;

    private String codeFiscal;

    @JsonIgnore
    private AdresseDto adresse;

    private String photo;

    private String mail;

    private String numTel;

    private Integer idEntreprise;


    public static EntrepriseDto FromEntity(Entreprise entreprise) {

        if (entreprise == null) {
            return null;
        }

        return EntrepriseDto.builder()
                .id(entreprise.getId())
                .name(entreprise.getName())
                .description(entreprise.getDescription())
                .codeFiscal(entreprise.getCodeFiscal())
                .photo(entreprise.getPhoto())
                .mail(entreprise.getMail())
                .numTel(entreprise.getNumTel())
                .idEntreprise(entreprise.getIdEntreprise())
                .build();
    }

    public static Entreprise ToEntity(EntrepriseDto entrepriseDto) {

        if (entrepriseDto == null) {
            return null;
        }

        Entreprise entreprise = new Entreprise();

        entreprise.setId(entrepriseDto.getId());
        entreprise.setName(entrepriseDto.getName());
        entreprise.setDescription(entrepriseDto.getDescription());
        entreprise.setCodeFiscal(entrepriseDto.getCodeFiscal());
        entreprise.setPhoto(entrepriseDto.getPhoto());
        entreprise.setMail(entrepriseDto.getMail());
        entreprise.setNumTel(entrepriseDto.getNumTel());
        entreprise.setIdEntreprise(entrepriseDto.getIdEntreprise());

        return entreprise;
    }
    


}
