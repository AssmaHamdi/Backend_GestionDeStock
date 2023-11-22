package com.hamdi.gestiondestock.controller.api;

import com.hamdi.gestiondestock.dto.VentesDto;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.hamdi.gestiondestock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/ventes")
public interface VentesApi {

    @PostMapping(value = APP_ROOT + "/ventes/create")
    VentesDto save(@RequestBody VentesDto dto);

    @GetMapping(value = APP_ROOT + "/ventes/{id}")
    VentesDto findById(@PathVariable Integer id);

    @GetMapping(value = APP_ROOT + "/ventes/{code}")
    VentesDto findByCode(@PathVariable String code);

    @GetMapping(value = APP_ROOT + "/ventes/all")
    List<VentesDto> findAll();

    @DeleteMapping(value =  APP_ROOT + "/ventes/delete/{id}")
    void delete(@PathVariable Integer id);
}
