package com.hamdi.gestiondestock.repository;

import com.hamdi.gestiondestock.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository< Entreprise, Integer> {
}
