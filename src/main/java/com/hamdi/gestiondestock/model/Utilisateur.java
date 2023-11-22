package com.hamdi.gestiondestock.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="Utilisateur")
public class Utilisateur extends AbstractEntity{

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Embedded
    private Adresse adresse;

    @Column(name = "photo")
    private String photo;

    @Column(name = "mail")
    private String mail;

    @Column(name = "datedenaissance")
    private Instant dateDeNaissance;

    @Column(name="motdepasse")
    private String motDePasse;

    @ManyToOne
    @JoinColumn(name ="idEntreprisee")
    private Entreprise Entreprise;

    @OneToMany(mappedBy = "Utilisateur")
    private List<Roles> Roles;

    @Column(name= "idEntreprise")
    private Integer idEntreprise;

}
