package com.hamdi.gestiondestock.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name="Ventes")
public class Ventes extends AbstractEntity{

    @OneToMany(mappedBy = "vente")
    private List<LigneVente> ligneVentes;

    @Column(name="code")
    private String code;

    @Column(name="datevente")
    private Instant datevente;

    @Column(name="commentaire")
    private String commentaire;

    @Column(name = "identreprise")
    private Integer idEnreprise;


}
