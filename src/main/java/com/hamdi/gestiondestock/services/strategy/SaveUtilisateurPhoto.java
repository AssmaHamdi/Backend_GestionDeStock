package com.hamdi.gestiondestock.services.strategy;

import com.flickr4java.flickr.FlickrException;
import com.hamdi.gestiondestock.dto.ArticleDto;
import com.hamdi.gestiondestock.dto.UtilisateurDto;
import com.hamdi.gestiondestock.exception.ErrorCodes;
import com.hamdi.gestiondestock.exception.InvalidOperationException;
import com.hamdi.gestiondestock.services.FlickrService;
import com.hamdi.gestiondestock.services.UtilisateurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("utilisateurStrategy")
@Slf4j
public class SaveUtilisateurPhoto implements Strategy<UtilisateurDto>{

    private FlickrService flickrService;
    private UtilisateurService utilisateurService;

    @Autowired
    public SaveUtilisateurPhoto(FlickrService flickrService, UtilisateurService utilisateurService) {
        this.flickrService = flickrService;
        this.utilisateurService=utilisateurService;
    }

    @Override
    public UtilisateurDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException {
        UtilisateurDto utilisateur = utilisateurService.findById(id);
        String urlphoto = flickrService.savePhoto(photo, title);
        if (!StringUtils.hasLength(urlphoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'article", ErrorCodes.UPDATE_PHOTO_EXCEPTID);
        }
        utilisateur.setPhoto(urlphoto);
        return utilisateurService.save(utilisateur);
    }
}
