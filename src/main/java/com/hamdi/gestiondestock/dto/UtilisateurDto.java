package com.hamdi.gestiondestock.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hamdi.gestiondestock.model.Client;
import com.hamdi.gestiondestock.model.Entreprise;
import com.hamdi.gestiondestock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.List;

@Builder
@Data
public class UtilisateurDto {

    private Integer id;

    private String nom;

    private String prenom;

    @JsonIgnore
    private AdresseDto adresse;

    private String photo;

    private String mail;

    private Instant dateDeNaissance;

    private String motDePasse;

    @JsonIgnore
    private EntrepriseDto Entreprise;

    @JsonIgnore
    private List<RolesDto> Roles;

    private Integer idEntreprise;


    public static UtilisateurDto FromEntity(Utilisateur utilisateur) {

        if (utilisateur == null) {
            return null;
        }

        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .photo((utilisateur.getPhoto()))
                .mail(utilisateur.getMail())
                .motDePasse(utilisateur.getMotDePasse())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .dateDeNaissance(utilisateur.getDateDeNaissance())
                .idEntreprise(utilisateur.getIdEntreprise())
                .build();
    }

    public static Utilisateur ToEntity(UtilisateurDto utilisateurDto) {

        if (utilisateurDto == null) {
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setMail(utilisateurDto.getMail());
        utilisateur.setMotDePasse(utilisateurDto.getMotDePasse());
        utilisateur.setDateDeNaissance(utilisateur.getDateDeNaissance());
        utilisateur.setIdEntreprise(utilisateurDto.getIdEntreprise());

        return utilisateur;

    }


}
