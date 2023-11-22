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
@Table(name="CommandeClient")
public class CommandeClient extends AbstractEntity{

    private String code;

    private Instant dateCommande;

    @ManyToOne
    @JoinColumn(name="idclient")
    private Client client;

    @OneToMany(mappedBy ="commandeclient")
    private List<LigneCommandeClient> ligneCommandeClient;

    @Column(name = "identreprise")
    private Integer idEnreprise;


}
