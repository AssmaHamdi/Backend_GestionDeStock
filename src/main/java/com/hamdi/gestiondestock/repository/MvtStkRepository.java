package com.hamdi.gestiondestock.repository;

import com.hamdi.gestiondestock.model.MvtStk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MvtStkRepository extends JpaRepository< MvtStk, Integer> {
}
