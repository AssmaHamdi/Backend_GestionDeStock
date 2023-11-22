package com.hamdi.gestiondestock.controller.api;

import com.hamdi.gestiondestock.dto.ClientDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hamdi.gestiondestock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/clients")
public interface ClientApi {

    @PostMapping(value = APP_ROOT + "/clients/create")
    ClientDto save(@RequestBody ClientDto dto);

    @GetMapping(value = APP_ROOT+ "/clients/{idClient}")
    ClientDto findById(@PathVariable Integer idClient);

    @GetMapping(value = APP_ROOT + "/clients/all")
    List<ClientDto> findAll();

    @DeleteMapping(value = APP_ROOT+ "/clients/delete/{idClient}")
    void delete(@PathVariable("idClient") Integer id);
}
