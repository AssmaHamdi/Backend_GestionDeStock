package com.hamdi.gestiondestock.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="Roles")
public class Roles extends AbstractEntity{

    @Column(name="rolename")
    private  String RoleName;

    @ManyToOne
    @JoinColumn(name="idUtilisateur")
    private Utilisateur Utilisateur;

    @Column(name = "identreprise")
    private Integer idEnreprise;
}
