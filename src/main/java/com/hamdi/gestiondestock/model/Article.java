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
@Table(name="Article")
public class Article extends AbstractEntity {

    @Column(name = "codeArticle")
    private String codeArticle;

    @Column(name = "designation")
    private String designation;

    @Column(name = "prixUnitaireHt")
    private BigDecimal prixUnitaireHt;

    @Column(name = "tauxTva")
    private BigDecimal tauxTva;

    @Column(name = "prixUnitaireTtc")
    private BigDecimal prixUnitaireTtc;

    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name="idCategory")
    private Category category;

    @Column(name = "identreprise")
    private Integer idEnreprise;




}
