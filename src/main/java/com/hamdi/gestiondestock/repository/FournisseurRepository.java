package com.hamdi.gestiondestock.repository;

import com.hamdi.gestiondestock.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FournisseurRepository extends JpaRepository< Fournisseur, Integer> {
}
