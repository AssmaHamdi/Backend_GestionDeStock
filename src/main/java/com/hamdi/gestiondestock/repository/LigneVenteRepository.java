package com.hamdi.gestiondestock.repository;

import com.hamdi.gestiondestock.model.LigneVente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneVenteRepository extends JpaRepository< LigneVente, Integer> {
}
