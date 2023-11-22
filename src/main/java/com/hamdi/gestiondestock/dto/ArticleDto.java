package com.hamdi.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hamdi.gestiondestock.model.Article;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder

public class ArticleDto {

    private Integer id;

    private String codeArticle;

    private String designation;

    private BigDecimal prixUnitaireHt;

    private BigDecimal tauxTva;

    private BigDecimal prixUnitaireTtc;

    private String photo;

    @JsonIgnore
    private CategoryDto category;

    private Integer idEntreprise;

    public static ArticleDto FromEntity(Article article) {
         if (article == null) {
             return null;
         }

         return ArticleDto.builder()
                 .id(article.getId())
                 .codeArticle(article.getCodeArticle())
                 .designation(article.getDesignation())
                 .prixUnitaireHt(article.getPrixUnitaireHt())
                 .tauxTva(article.getTauxTva())
                 .prixUnitaireTtc(article.getPrixUnitaireTtc())
                 .photo(article.getPhoto())
                 .idEntreprise(article.getIdEnreprise())
                 .build();
    }
    public static Article ToEntity(ArticleDto articleDto) {

        if (articleDto == null) {
            return  null;
        }

        Article article = new Article();
        article.setId(articleDto.getId());
        article.setCodeArticle(articleDto.getCodeArticle());
        article.setDesignation(articleDto.getDesignation());
        article.setPrixUnitaireHt(article.getPrixUnitaireHt());
        article.setTauxTva(articleDto.getTauxTva());
        article.setPrixUnitaireTtc(articleDto.getPrixUnitaireTtc());
        article.setPhoto(articleDto.getPhoto());
        article.setIdEnreprise(articleDto.getIdEntreprise());

        return article;
    }

}
