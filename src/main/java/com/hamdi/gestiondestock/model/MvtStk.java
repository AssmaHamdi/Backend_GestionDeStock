package com.hamdi.gestiondestock.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="MvtStk")
public class MvtStk extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name= "idarticle")
    private Article article;

    @Column(name = "dtemvt")
    private Instant dteMvt;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name="typemvtstk")
    private TypeMvtStk typemvtStk;

    @Column(name = "identreprise")
    private Integer idEnreprise;


}
