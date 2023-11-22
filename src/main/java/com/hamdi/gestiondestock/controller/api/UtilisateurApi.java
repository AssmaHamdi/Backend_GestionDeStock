package com.hamdi.gestiondestock.controller.api;

import com.hamdi.gestiondestock.dto.UtilisateurDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hamdi.gestiondestock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/utilisateurs")
public interface UtilisateurApi {

    @PostMapping(value = APP_ROOT + "/utilisateurs/create")
    UtilisateurDto save(@RequestBody UtilisateurDto dto);

    @GetMapping(value = APP_ROOT + "/utilisateurs/{idUtilisateur}")
    UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);

    @GetMapping(value = APP_ROOT + "/utilisateurs/all")
    List<UtilisateurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/utilisateurs/delete/{idUtilisateur}")
    void delete(@PathVariable("idUtilisateur") Integer id);
}
