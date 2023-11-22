package com.hamdi.gestiondestock.controller.api;

import com.hamdi.gestiondestock.dto.CategoryDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hamdi.gestiondestock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/categories")
public interface CategoryApi {

    @PostMapping(value = APP_ROOT + "/categories/create")
    @CrossOrigin(origins = "http://localhost:4200")
    CategoryDto save(@RequestBody CategoryDto dto);

    @GetMapping(value = APP_ROOT + "/categories/{idArticle}")
    CategoryDto findById(@PathVariable("idArticle") Integer id);

    @GetMapping(value = APP_ROOT + "/categories/{codeArticle}/")
    CategoryDto findByCode(@PathVariable("codeArticle") String code);

    @GetMapping(value = APP_ROOT +"/categories/all")
    List<CategoryDto> findAll();

    @DeleteMapping(value = APP_ROOT + "categories/delete/{idArticle}")
    void delete(@PathVariable("idArticle") Integer id);
}
