package com.hamdi.gestiondestock.controller.api;

import com.hamdi.gestiondestock.dto.CommandeClientDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hamdi.gestiondestock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/commandesclients")
public interface CommandeClientApi {

    @PostMapping(value = APP_ROOT + "/commandesclients/create")
    CommandeClientDto save(@RequestBody CommandeClientDto dto);

    @GetMapping(value = APP_ROOT + "/commandesclients/{id}")
    CommandeClientDto findById(@PathVariable Integer id);

    @GetMapping(value = APP_ROOT + "/commandesclients/{code}")
    CommandeClientDto findByCode(@PathVariable String code);

    @GetMapping(value = APP_ROOT + "/commandesclients/all")
    List<CommandeClientDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/commandesclients/delete/{id}")
    void delete(@PathVariable Integer id);
}
