package com.hamdi.gestiondestock.controller.api;
import com.hamdi.gestiondestock.dto.ArticleDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.hamdi.gestiondestock.utils.Constants.APP_ROOT;

@Api(APP_ROOT +"/articles")
public interface ArticleApi {

    @PostMapping(value = APP_ROOT + "/articles/create" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un article" , notes = "cette methode permet d'enregistrer ou de modifier un article" , response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet article cree/modifie"),
            @ApiResponse(code = 400, message = "L'objet article n'est pas valide")
    })
    ArticleDto save(@RequestBody ArticleDto dto);


    @GetMapping(value = APP_ROOT + "/articles/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un article par Id" , notes = "cette methode permet de rechercher un article par son Id" , response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article a été trouve dans le BDD"),
            @ApiResponse(code = 404, message = "aucun article n'esxiste dans la BDD avec l'id fournit")
    })
    ArticleDto findById(@PathVariable("idArticle") Integer id);



    @GetMapping(value = APP_ROOT + "/articles/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un article par code" , notes = "cette methode permet de rechercher un article par son code" , response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article a été trouve dans le BDD"),
            @ApiResponse(code = 404, message = "aucun article n'esxiste dans la BDD avec le code fournit")
    })
    ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticle);



    @GetMapping(value = APP_ROOT + "/articles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des articles" , notes = "cette methode permet de renvoyer la liste article qui existent dans la BDD" ,
            responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la liste des articles / une liste vide")
    })
    List<ArticleDto> findAll();


    @DeleteMapping(value = APP_ROOT+"/articles/delete/{idArticle}" )
    @ApiOperation(value = "Supprimer un article" , notes = "cette methode permet de supprimer un article par son id ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "l'article a été supprimer'")
    })
    void delete(@PathVariable("idArticle") Integer id);
}
