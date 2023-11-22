package com.hamdi.gestiondestock.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="Entreprise")
public class Entreprise extends AbstractEntity{

    @Column(name="name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name="codefiscal")
    private String codeFiscal;

    @Embedded
    private Adresse adresse;

    @Column(name = "photo")
    private String photo;

    @Column(name = "mail")
    private String mail;

    @Column(name = "numTel")
    private String numTel;

    @Column(name="siteweb")
    private String siteweb;

    @OneToMany(mappedBy = "Entreprise")
    private List<Utilisateur> utilisateur;

    @Column(name ="idEntreprise")
    private Integer idEntreprise;


}
