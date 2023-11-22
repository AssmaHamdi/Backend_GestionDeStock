package com.hamdi.gestiondestock.services.strategy;

import com.flickr4java.flickr.FlickrException;
import com.hamdi.gestiondestock.dto.ArticleDto;
import com.hamdi.gestiondestock.exception.ErrorCodes;
import com.hamdi.gestiondestock.exception.InvalidOperationException;
import com.hamdi.gestiondestock.services.ArticleService;
import com.hamdi.gestiondestock.services.FlickrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("articleStrategy")
@Slf4j
public class SaveArticlePhoto implements Strategy<ArticleDto>{

    private FlickrService flickrService;
    private ArticleService articleService;

    @Autowired
    public SaveArticlePhoto(ArticleService articleService, FlickrService flickrService) {
        this.articleService = articleService;
        this.flickrService = flickrService;
    }

    @Override
    public ArticleDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException {

        ArticleDto article = articleService.findById(id);
        String urlphoto = flickrService.savePhoto(photo, title);
        if (!StringUtils.hasLength(urlphoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'article", ErrorCodes.UPDATE_PHOTO_EXCEPTID);
        }
        article.setPhoto(urlphoto);
        return articleService.save(article);

    }
}
