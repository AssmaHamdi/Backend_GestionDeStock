package com.hamdi.gestiondestock.repository;

import com.hamdi.gestiondestock.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository< Utilisateur, Integer> {
}
