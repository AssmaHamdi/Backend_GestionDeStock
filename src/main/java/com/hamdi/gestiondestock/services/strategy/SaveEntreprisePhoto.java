package com.hamdi.gestiondestock.services.strategy;

import com.flickr4java.flickr.FlickrException;
import com.hamdi.gestiondestock.dto.EntrepriseDto;
import com.hamdi.gestiondestock.exception.ErrorCodes;
import com.hamdi.gestiondestock.exception.InvalidOperationException;
import com.hamdi.gestiondestock.services.EntrepriseService;
import com.hamdi.gestiondestock.services.FlickrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("entrepriseStrategy")
@Slf4j
public class SaveEntreprisePhoto implements Strategy<EntrepriseDto>{

    private FlickrService flickrService;
    private EntrepriseService entrepriseService;

    @Autowired
    public SaveEntreprisePhoto(FlickrService flickrService, EntrepriseService entrepriseService) {
        this.flickrService = flickrService;
        this.entrepriseService=entrepriseService;
    }

    @Override
    public EntrepriseDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException {
        EntrepriseDto entreprise = entrepriseService.findById(id);
        String urlphoto = flickrService.savePhoto(photo, title);
        if (!StringUtils.hasLength(urlphoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'article", ErrorCodes.UPDATE_PHOTO_EXCEPTID);
        }
        entreprise.setPhoto(urlphoto);
        return entrepriseService.save(entreprise);
    }
}
