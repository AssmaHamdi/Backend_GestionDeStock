package com.hamdi.gestiondestock.controller.api;

import com.hamdi.gestiondestock.dto.CommandeFournisseurDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hamdi.gestiondestock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/commandesFournisseurs" )
public interface CommandeFournisseurApi {

    @PostMapping(value = APP_ROOT + "/commandesFournisseurs/create")
    CommandeFournisseurDto save(@RequestBody CommandeFournisseurDto dto);

    @GetMapping(value = APP_ROOT +"commandesFournisseurs/{id}")
    CommandeFournisseurDto findById(@PathVariable Integer id);

    @GetMapping(value = APP_ROOT + "commandesFournisseurs/{code}")
    CommandeFournisseurDto findByCode(@PathVariable String code);

    @GetMapping(value = APP_ROOT + "commandesFournisseurs/all")
    List<CommandeFournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/commandesFournisseurs/delete/{id}")
    void delete(@PathVariable Integer id);
}
