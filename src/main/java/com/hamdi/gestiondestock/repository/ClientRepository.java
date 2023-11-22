package com.hamdi.gestiondestock.repository;

import com.hamdi.gestiondestock.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository< Client,Integer> {
}
