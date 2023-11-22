package com.hamdi.gestiondestock.services.strategy;

import com.flickr4java.flickr.FlickrException;
import com.hamdi.gestiondestock.dto.ArticleDto;
import com.hamdi.gestiondestock.dto.FournisseurDto;
import com.hamdi.gestiondestock.exception.ErrorCodes;
import com.hamdi.gestiondestock.exception.InvalidOperationException;
import com.hamdi.gestiondestock.services.FlickrService;
import com.hamdi.gestiondestock.services.FournisseurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("fournisseurStrategy")
@Slf4j
public class SaveFournisseurPhoto implements Strategy<FournisseurDto>{

    private FournisseurService fournisseurService;
    private FlickrService flickrService;

    @Autowired
    public SaveFournisseurPhoto(FlickrService flickrService, FournisseurService fournisseurService) {
        this.flickrService = flickrService;
        this.fournisseurService=fournisseurService;
    }

    @Override
    public FournisseurDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException {
        FournisseurDto fournisseur = fournisseurService.findById(id);
        String urlphoto = flickrService.savePhoto(photo, title);
        if (!StringUtils.hasLength(urlphoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'article", ErrorCodes.UPDATE_PHOTO_EXCEPTID);
        }
        fournisseur.setPhoto(urlphoto);
        return fournisseurService.save(fournisseur);
    }
}
