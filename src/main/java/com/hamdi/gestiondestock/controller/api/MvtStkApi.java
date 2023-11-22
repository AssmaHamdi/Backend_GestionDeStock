package com.hamdi.gestiondestock.controller.api;

import com.hamdi.gestiondestock.dto.MvtStkDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hamdi.gestiondestock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/mvtstks")
public interface MvtStkApi {

    @PostMapping(value = APP_ROOT + "/mvtstks/create")
    MvtStkDto save(@RequestBody MvtStkDto dto);

    @GetMapping(value = APP_ROOT + "/mvtstks/{idMvtStk}")
    MvtStkDto findById(@PathVariable("idMvtStk") Integer id);

    @GetMapping(value = APP_ROOT + "/mvtstks/all")
    List<MvtStkDto> findAll();

    @DeleteMapping(value =  APP_ROOT + "/mvtstks/{idMvtStk}")
    void delete(@PathVariable("idMvtStk") Integer id);
}
