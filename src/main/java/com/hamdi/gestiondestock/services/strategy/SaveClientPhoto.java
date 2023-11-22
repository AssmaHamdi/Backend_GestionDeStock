package com.hamdi.gestiondestock.services.strategy;

import com.flickr4java.flickr.FlickrException;
import com.hamdi.gestiondestock.dto.ClientDto;
import com.hamdi.gestiondestock.exception.ErrorCodes;
import com.hamdi.gestiondestock.exception.InvalidOperationException;
import com.hamdi.gestiondestock.services.ClientService;
import com.hamdi.gestiondestock.services.FlickrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("clientStrategy")
@Slf4j
public class SaveClientPhoto implements Strategy<ClientDto>{

    private FlickrService flickrService;
    private ClientService clientService;

    @Autowired
    public SaveClientPhoto(ClientService clientService, FlickrService flickrService) {
        this.clientService = clientService;
        this.flickrService = flickrService;
    }

    @Override
    public ClientDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException {
        ClientDto client = clientService.findById(id);
        String urlphoto = flickrService.savePhoto(photo, title);
        if (!StringUtils.hasLength(urlphoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'article", ErrorCodes.UPDATE_PHOTO_EXCEPTID);
        }
        client.setPhoto(urlphoto);
        return clientService.save(client);
    }
}
