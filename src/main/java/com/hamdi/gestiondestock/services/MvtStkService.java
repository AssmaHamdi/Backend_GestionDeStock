package com.hamdi.gestiondestock.services;

import com.hamdi.gestiondestock.dto.MvtStkDto;

import java.util.List;

public interface MvtStkService {

    MvtStkDto save(MvtStkDto dto);

    MvtStkDto findById(Integer id);

    List<MvtStkDto> findAll();

    void delete(Integer id);
}
