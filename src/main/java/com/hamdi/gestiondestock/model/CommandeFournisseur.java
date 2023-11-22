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
@Table(name="CommandeFournisseur")
public class CommandeFournisseur extends  AbstractEntity{

    private String code;

    private Instant dateCommande;

    @ManyToOne
    @JoinColumn(name="idfournisseur")
    private Fournisseur fournisseur;

    @OneToMany(mappedBy = "commandefournisseur")
    private List<LigneCommandeFournisseur> ligneCommandeFournisseur;

    @Column(name = "identreprise")
    private Integer idEnreprise;

}
