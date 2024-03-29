package com.hamdi.gestiondestock.repository;

import com.hamdi.gestiondestock.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeClientRepository extends JpaRepository< CommandeClient, Integer> {

    Optional<CommandeClient> findCommandeClientByCode(String code);
}
