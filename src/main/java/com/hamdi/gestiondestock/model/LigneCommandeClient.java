package com.hamdi.gestiondestock.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="LigneCommandeClient")
public class LigneCommandeClient extends  AbstractEntity{

    @ManyToOne
    @JoinColumn(name= "idarticle")
    private Article article;

    @ManyToOne
    @JoinColumn(name="idcommandeclient")
    private CommandeClient commandeclient;

    @Column(name="quantite")
    private BigDecimal quantite;

    @Column(name="prixunitaire")
    private BigDecimal prixUnitaire;

    @Column(name = "identreprise")
    private Integer idEnreprise;


}
