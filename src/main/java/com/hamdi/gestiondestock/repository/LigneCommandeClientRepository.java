package com.hamdi.gestiondestock.repository;

import com.hamdi.gestiondestock.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeClientRepository extends JpaRepository< LigneCommandeClient,Integer> {
}
